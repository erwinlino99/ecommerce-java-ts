package com.ecommerce.backend.models;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ecommerce.backend.config.SpringContext;
import com.ecommerce.backend.rest_controllers.DatabaseConnection;

public class BaseModel {

    protected String tableName;
    protected String alias;
    protected static JdbcTemplate jdbc;

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
}
