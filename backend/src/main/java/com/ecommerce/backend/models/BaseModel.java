package com.ecommerce.backend.models;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ecommerce.backend.config.SpringContext;
import com.ecommerce.backend.controllers.DatabaseConnection;

public class BaseModel {

    protected String tableName;
    protected String alias;
    protected static JdbcTemplate jdbc;

    @SuppressWarnings("static-access")
    public BaseModel(String tableName, String alias) {
        this.tableName = tableName;
        this.alias = alias;
        if (this.jdbc == null) {
            DatabaseConnection db = SpringContext.getBean(DatabaseConnection.class);
            this.jdbc = db.execute();
        }
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static void setDataBaseConnection(DatabaseConnection db) {
        BaseModel.jdbc = db.execute();
    }

    public Map<String, Object> getOne(int id) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE id = ?";
        return jdbc.queryForMap(sql, id);
    }

    public List<Map<String, Object>> getAll() {
        String sql = "SELECT * FROM " + this.tableName;
        return jdbc.queryForList(sql);
    }
    // =====================================================
    // MÉTODO NUEVO → INSERCIÓN GENÉRICA
    // =====================================================
    @SuppressWarnings("UseSpecificCatch")
    public boolean createNewRecord(Object payload) {
        if (payload == null) {
            return false;
        }
        // 1) Convertir el payload a Map<String,Object> (acepta Map o POJO)
        Map<String, Object> input = new LinkedHashMap<>();
        if (payload instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> tmp = (Map<String, Object>) payload;
            input.putAll(tmp);
        } else {
            try {
                for (Method m : payload.getClass().getMethods()) {
                    if (m.getParameterCount() == 0) {
                        String name = m.getName();
                        if (name.startsWith("get") && !name.equals("getClass")) {
                            String prop = Character.toLowerCase(name.charAt(3)) + name.substring(4);
                            Object val = m.invoke(payload);
                            if (val != null) {
                                input.put(prop, val);
                            }
                        } else if (name.startsWith("is")) {
                            String prop = Character.toLowerCase(name.charAt(2)) + name.substring(3);
                            Object val = m.invoke(payload);
                            if (val != null) {
                                input.put(prop, val);
                            }
                        }
                    }
                }
            } catch (Exception ignore) {
                return false;
            }
        }
        if (input.isEmpty()) {
            return false;
        }
        // 2) Obtener columnas reales de la tabla (sin alias, genérico)
        String showColsSql = "SHOW COLUMNS FROM " + this.tableName;
        List<Map<String, Object>> rows = jdbc.queryForList(showColsSql);
        Set<String> allowed = new LinkedHashSet<>();
        for (Map<String, Object> r : rows) {
            Object f = r.get("Field"); // MySQL devuelve "Field"
            if (f != null) {
                String col = String.valueOf(f);
                if (!"id".equalsIgnoreCase(col)) { // opcional: evitar ID autoincrement
                    allowed.add(col);
                }
            }
        }
        // 3) Filtrar solo claves que existan en la tabla
        Map<String, Object> finalData = new LinkedHashMap<>();
        for (Map.Entry<String, Object> e : input.entrySet()) {
            if (e.getValue() == null) {
                continue;
            }
            if (allowed.contains(e.getKey())) {
                finalData.put(e.getKey(), e.getValue());
            }
        }

        if (finalData.isEmpty()) {
            return false;
        }
        // 4) Construir SQL de inserción dinámicamente
        List<String> cols = new ArrayList<>(finalData.keySet());
        String colList = String.join(",", cols);
        String qMarks = String.join(",", Collections.nCopies(cols.size(), "?"));
        String sql = "INSERT INTO " + this.tableName + " (" + colList + ") VALUES (" + qMarks + ")";

        List<Object> vals = new ArrayList<>(finalData.values());
        // 5) Ejecutar sentencia
        int affected = jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < vals.size(); i++) {
                ps.setObject(i + 1, vals.get(i));
            }
            return ps;
        });
        return affected > 0;
    }
    public boolean getOneByFilter(Object payload) {
        return true;
    }
}
