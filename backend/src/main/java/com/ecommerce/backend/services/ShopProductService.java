package com.ecommerce.backend.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.backend.dto.ShopProductDto;
import com.ecommerce.backend.dto.mapper.ShopProductMapper;
import com.ecommerce.backend.dto.request.ShopProductImportRequest;
import com.ecommerce.backend.dto.request.ShopProductRequest;
import com.ecommerce.backend.models.ShopProduct;
import com.ecommerce.backend.models.ShopProductBrand;
import com.ecommerce.backend.models.ShopProductMeasurement;
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
        product.setShortDescription(request.shortDescription());
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
        List<ShopProduct> products = this.repo.findByDeletedIsNull();
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

    public ResponseEntity deletedShopProduct(Integer shopProductId) {
        ShopProduct product = this.repo.findById(shopProductId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(LocalDateTime.now());
        this.repo.save(product);
        return ResponseEntity.ok(Map.of("ok", "Producto eliminado"));
    }

    public void createOrUpdate(ShopProductImportRequest shopProductImport, ShopProductBrand shopProductBrand,
            ShopProductMeasurement shopProductMeasurement) {
        // PRIMERO TENEMOS QUE HACER UNA CONSULTA A BASE DE DATOS
        Optional<ShopProduct> existingProduct = repo.findByNameIgnoreCase(shopProductImport.name());
        ShopProduct product;
        if (existingProduct.isPresent()) {
            // RECUPERAMOS EL REGISTRO DE LA BASE DE DATOS
            // ACTUALIZAMOS EL STOCK Y EL PRECIO SEGUN EL EXCEL
            product = existingProduct.get();
            product.setCurrentStock(shopProductImport.currentStock());
            product.setPrice(shopProductImport.price());

        } else {
            product = new ShopProduct();
            product.setName(shopProductImport.name());
            product.setDescription(shopProductImport.name());
            product.setShortDescription(shopProductImport.shortDescription());
            product.setShopProductBrand(shopProductBrand);
            product.setMeasurement(shopProductMeasurement);
            product.setCurrentStock(shopProductImport.currentStock());
            product.setPrice(shopProductImport.price());
        }
        this.repo.save(product);

    }
}
