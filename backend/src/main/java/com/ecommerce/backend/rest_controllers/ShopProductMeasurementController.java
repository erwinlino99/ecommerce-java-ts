package com.ecommerce.backend.rest_controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.models.ShopProductMeasurement;
import com.ecommerce.backend.services.ShopProductMeasurementService;

@RestController
//ESTO SERIA COMO EL PADRE Y APARTIR DE ESTO SERIA LA RUTA PADRE + GetPutDelteMapping ("/ejemplo")
// @RequestMapping("/shop_product_measurement")
@CrossOrigin
public class ShopProductMeasurementController {

    //INYECTAMOS EL SERVICIO
    private final ShopProductMeasurementService service;

    //DENTRO DEL CONSTRUCTOR LO INICIALIZAMOS
    public ShopProductMeasurementController(ShopProductMeasurementService service) {
        this.service = service;
    }

    //HACEMOS EL GETMAPPING LLAMANDO AL SERVICE CON EL METODO QUE NOS INTERESA
    @GetMapping("/shop_product_measurement")
    public List<ShopProductMeasurement> getAll() {
        return this.service.getAllRecord();
    }

}
