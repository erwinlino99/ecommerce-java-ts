package com.ecommerce.backend.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.backend.util.UseLogger;

@Service
public class ImportFileService {

    public ResponseEntity getXlsxTemplate() {
        try {
            Resource template = new ClassPathResource("util/web_template.xlsx");
            if (!template.exists()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "EL ARCHIVO NO EXISTE O RUTA ERRONEA");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"plantilla_productos.xlsx\"")
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(template);

        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Ha ocurrido un fallo inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public ResponseEntity readXlsxTemplate(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;
                String name = (row.getCell(0) != null) ? row.getCell(0).getStringCellValue() : "S/N";
                String description = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "S/D";
                String shortDescription = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "S/D";
                double price = (row.getCell(3) != null) ? row.getCell(3).getNumericCellValue() : 0.0;
                double stock = (row.getCell(4) != null) ? row.getCell(4).getNumericCellValue() : 0.0;
                String brand = (row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "Genérica";
                String measurementName = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "Genérica";
                Integer measurementUnit = (row.getCell(7) != null) ? (int) row.getCell(7).getNumericCellValue() : 0;

                UseLogger.info("NOMBRE FILA " + i, name);
                UseLogger.info("DESCRIPTION", description);
                UseLogger.info("SHORT_DESCRIPTION", shortDescription);
                UseLogger.info("PRECIO", price);
                UseLogger.info("STOCK", (int) stock);
                UseLogger.info("MARCA", brand);
                UseLogger.info("TIPO MEDIDA", measurementName);
                UseLogger.info("UNIDADES DENTRO", measurementUnit);
                System.out.println("-------------------------------------------");

                // Tengo pensado poner en la entidad o en el dto de la measuerment constantes de
                // nombres, en mi base de datos solo tengo , UDS, CAJA, PALLET,
                // Si la celda X no coicide con alguno de estas constantes echar para atras el
                // registro entero

                // public enum MeasurementType {
                // UDS, CAJA, PALLET;

                // // Método para validar si un texto existe en el Enum
                // public static boolean isValid(String value) {
                // for (MeasurementType type : MeasurementType.values()) {
                // if (type.name().equalsIgnoreCase(value)) {
                // return true;
                // }
                // }
                // return false;
                // }
                // }

            }

            return ResponseEntity.ok(Map.of("message", "Lectura completada exitosamente"));

        } catch (Exception e) {
            UseLogger.error("FALLO CRÍTICO AL LEER EXCEL", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
