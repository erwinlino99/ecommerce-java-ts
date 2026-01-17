package com.ecommerce.backend.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.ecommerce.backend.dto.enums.ShopProductMeasurementEnum;
import com.ecommerce.backend.dto.request.ShopProductImportRequest;
import com.ecommerce.backend.exceptions.ImportException;
import com.ecommerce.backend.models.ShopProductBrand;
import com.ecommerce.backend.models.ShopProductMeasurement;

import jakarta.validation.Validator;

@Service
public class ImportFileService {

    // INYECTAMOS UN VALIDADOR
    @Autowired
    private Validator validator;
    private final ShopProductBrandService brandService;
    private final ShopProductService productService;

    public ImportFileService(ShopProductBrandService brandService,
            ShopProductService productService) {
        this.brandService = brandService;
        this.productService = productService;

    }

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
        List<String> errors = new ArrayList<>();
        Integer success = 0;
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                // PASO 1 : MEDIDA CORRECTA
                String measurementName = (row.getCell(6) != null) ? row.getCell(6).getStringCellValue() : "";
                if (measurementName.isBlank() || !ShopProductMeasurementEnum.isValid(measurementName)) {
                    errors.add("Fila :" + (i + 1)
                            + ". EL FORMATO DE LA MEDIDA NO ES CORRECTO. NO SE HA IMPORTADO");
                    continue;
                }

                // PASO 2: SACAR TODAS LAS CELDAS
                String name = (row.getCell(0) != null) ? row.getCell(0).getStringCellValue() : "";
                String description = (row.getCell(1) != null) ? row.getCell(1).getStringCellValue() : "";
                String shortDescription = (row.getCell(2) != null) ? row.getCell(2).getStringCellValue() : "";
                Double price = (row.getCell(3) != null) ? row.getCell(3).getNumericCellValue() : 0.0;
                Double stock = (row.getCell(4) != null) ? row.getCell(4).getNumericCellValue() : 0.0;
                String brandName = (row.getCell(5) != null) ? row.getCell(5).getStringCellValue() : "";

                // PASO 3: CONSTRUIMOS EL PRODUCTO REQUEST PARA PODER VALIDARLO Y SACAR POSIBLES
                // ERRORES
                ShopProductImportRequest productImport = new ShopProductImportRequest(name, description,
                        shortDescription,
                        price, stock.intValue(), brandName, measurementName);

                // PASO 4: AGREGAMOS MENSAJES DE POSIBLES ERRORES
                var violations = validator.validate(productImport);
                if (!violations.isEmpty()) {
                    String msm = violations.iterator().next().getMessage();
                    errors.add("Fila " + (i + 1) + ": " + msm);
                    continue;
                }
                // PASO 5: VEMOS SI LA MARCA EXISTE O LA CREAMOS
                ShopProductBrand shopProductBrand = this.brandService.getOrCreateBrand(brandName);
                // PASO 6: CREAMOS EL ShopProductMeasurement
                // NOTA: CON SETEAR EL ID DE LA BASE DE DATOS YA VALE PARA LUEGO
                ShopProductMeasurement validShopProductBrand = new ShopProductMeasurement();
                validShopProductBrand.setId(ShopProductMeasurementEnum.getId(measurementName));
                // PASO 7: CREAMOS O ACTUALIZAMOS STOCK DEL SHOP-PRODUCT
                this.productService.createOrUpdate(productImport, shopProductBrand, validShopProductBrand);
                success++;
            }
            if (!errors.isEmpty()) {
                throw new ImportException(errors, success);
            }
            return ResponseEntity.ok(Map.of("message", "Lectura completada exitosamente"));

        } catch (ImportException ex) {
            throw ex;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
