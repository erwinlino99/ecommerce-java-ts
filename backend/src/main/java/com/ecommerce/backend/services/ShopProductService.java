package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.dto.mapper.ShopProductMapper;
import com.ecommerce.backend.dto.request.ShopProductRequest;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.repositories.ShopProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShopProductService {

    private final ShopProductRepository repo;

    public ShopProductService(ShopProductRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public ShopProductDto updateShopProduct(Integer shopProductId, ShopProductRequest request) {

        ShopProduct product = this.repo.findById(shopProductId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + shopProductId));

        product.setName(request.name());
        product.setShort_description(request.shortDescription());
        product.setDescription(request.description());
        product.setCurrentStock(request.currentStock());
        product.setPrice(request.price());

        if (request.deleted() != null && !request.deleted().isBlank() && product.getDeleted() == null) {
            product.setDeleted(java.time.LocalDateTime.now());
        } else if ((request.deleted() == null || request.deleted().isBlank()) && product.getDeleted() != null) {
            product.setDeleted(null);
        }

        ShopProduct savedProduct = this.repo.save(product);
        return ShopProductMapper.toFullDto(savedProduct);
    }

    public List<ShopProductDto> getAllRecords() {
        List<ShopProduct> products = this.repo.findAll();
        return products.stream().map(p -> ShopProductMapper.toFullDto(p)).toList();
    }

    @SuppressWarnings("null")
    public ShopProductDto getShopProductById(Integer shopProductId) {
        ShopProduct p = this.repo.findById(shopProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ShopProductDto(
                p.getId(),
                p.getName(),
                p.getBrandName(),
                p.getDescription(),
                p.getShortDescription(),
                p.getPrice(),
                p.getCurrentStock(),
                p.getMeasurementName(),
                p.getMeasurementUnit(),
                p.getCreated(),
                p.getModified(),
                p.getDeleted());

    }
}
