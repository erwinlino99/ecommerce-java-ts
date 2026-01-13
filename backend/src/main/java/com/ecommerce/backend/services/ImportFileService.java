package com.ecommerce.backend.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.ecommerce.backend.dto.request.ShopProductImportRequest;
import com.ecommerce.backend.models.ShopProductBrand;
import com.ecommerce.backend.util.UseLogger;
import jakarta.validation.Validator;

@Service
public class ImportFileService {

    // INYECTAMOS UN VALIDADOR
    @Autowired
    private Validator validator;
    private ShopProductBrandService brandService;
    private ShopProductMeasurementService measurementService;

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

        } catch (ResponseStatusException e) {
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
                System.out.println("EMPEZAMOS A PROCESAR");
                if (row == null)
                    continue;
                String name = (row.getCell(0) != null) ? row.getCell(0).getStringCellValue() : "S/N";
                String description = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "S/D";
                String shortDescription = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "S/D";
                Double price = (row.getCell(3) != null) ? row.getCell(3).getNumericCellValue() : 0.0;
                Double stock = (row.getCell(4) != null) ? row.getCell(4).getNumericCellValue() : 0.0;
                String brand = (row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "Genérica";
                String measurementName = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "Genérica";

                // DENTRO ESTAN LAS ANOTACIONES @NotBlank, @Positive @Min ...
                ShopProductImportRequest product = new ShopProductImportRequest(name, description, shortDescription,
                        price, stock.intValue(), brand, measurementName);
                // TENEMSO QUE ACTIVAR EL VALIDATOR.
                var badRequest = validator.validate(product);
                if (!badRequest.isEmpty()) {
                    // MOSTRAMOS LOS ERRORES
                }
                // this.measurementService.measurementNameValid(measurementName);

                //UNA VEZ SE HAN HECHO TODOS LOS CHECK , HACEMOS UNO FINAL DE LA MARCA
                //SI LA MARCA NO EXISTE LO CREAMOS Y LO ASOCIAMOS A UN NUEVO PRODUCTO ENTERO
                // SI LA MARCA YA EXISTE SIMPLEMENTE ACTUALIZAMOS EL STOCK DEL MISMO PRODUCTO
                ShopProductBrand shopProductBrand=this.brandService.getOrCreateBrand(brand);

            }

            return ResponseEntity.ok(Map.of("message", "Lectura completada exitosamente"));

        } catch (Exception e) {
            UseLogger.error("FALLO CRÍTICO AL LEER EXCEL", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
