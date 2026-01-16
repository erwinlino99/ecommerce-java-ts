package com.ecommerce.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.SelectableDto;
import com.ecommerce.backend.models.ShopProductBrand;
import com.ecommerce.backend.repositories.ShopProductBrandRepository;

@Service
public class ShopProductBrandService {

    private final ShopProductBrandRepository repo;

    public ShopProductBrandService(ShopProductBrandRepository repo) {
        this.repo = repo;
    }

    public ShopProductBrand getOrCreateBrand(String shopProductBrandName) {
        // LLAMADA AL BASE DE DATOS MEDIANTE EL REPOSITORIO JPA DIRECTAMENTE
        return this.repo.findByNameIgnoreCase(shopProductBrandName)
                .orElseGet(() -> {
                    // SI NO EXISTE LO CREAMOS EN ESTE MOMENTO
                    ShopProductBrand newBrand = new ShopProductBrand();
                    newBrand.setName(shopProductBrandName);
                    return repo.save(newBrand);
                });
    }

    public List<SelectableDto> getSelectable() {
        return this.repo.findAll()
                .stream()
                .map(brand -> new SelectableDto(
                        brand.getId(),
                        brand.getName()))
                .collect(Collectors.toList());
    }
}
