package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopProductRepository;
@Service
public class ShopProductService {
    private final ShopProductRepository repo;

    public ShopProductService(ShopProductRepository repo) {
        this.repo = repo;
    }

    public List<ShopProductDto> getAllRecords() {
        //RECUPERAMOS TODOS LOS PRODUCTOS SEGUN LA ENTIDAD
        List<ShopProduct> products = this.repo.findAll();
        //DEVOLVEMOS SOLO EL DTO
        return products.stream().map(p -> new ShopProductDto(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getMeasurement() != null ? p.getMeasurement().getName() : null,
                p.getMeasurement() != null ? p.getMeasurement().getUnit() : null
        )).toList();

    }
}
