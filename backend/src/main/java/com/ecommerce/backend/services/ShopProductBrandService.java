package com.ecommerce.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.SelectableDto;
import com.ecommerce.backend.models.ShopProductBrand;
import com.ecommerce.backend.repositories.ShopProductBrandRepository;

@Service
public class ShopProductBrandService {

    private ShopProductBrandRepository repo;

    public ShopProductBrandService(ShopProductBrandRepository repo) {
        this.repo = repo;
    }

    public ShopProductBrand getOrCreateBrand(String shopProductBrandName){
        //LLAMAOS AL A BASE DE DATOS
        return this.repo.existsByNameIgnoreCase(shopProductBrandName);
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
