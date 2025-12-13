package com.ecommerce.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.ShopOrderDto;
import com.ecommerce.backend.dto.ShopOrderItemDto;
import com.ecommerce.backend.dto.ShopOrderStatusDto;
import com.ecommerce.backend.dto.ShopProductLiteDto;
import com.ecommerce.backend.models.ShopOrder;
import com.ecommerce.backend.models.ShopOrderItem;
import com.ecommerce.backend.repositories.ShopOrderRepository;

@Service
public class ShopOrderService {

    private ShopOrderRepository repo;

    public ShopOrderService(ShopOrderRepository repo) {
        this.repo = repo;
    }

    public List<ShopOrderDto> getAllShopOrdersDto() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public List<ShopOrderDto> getAllShopOrdersbyWebUserId(Integer webUserId) {
        return repo.findByWebUserId(webUserId).stream().map(this::toDto).toList();
    }

    private ShopOrderDto toDto(ShopOrder order) {

        List<ShopOrderItemDto> itemsDto = order.getItems().stream()
                .map(this::toItemDto)
                .toList();

        ShopOrderStatusDto statusDto = new ShopOrderStatusDto(
                order.getShopOrderStatus().getName()
        );

        return new ShopOrderDto(
                order.getId(),
                order.getTotalAmount(),
                itemsDto,
                statusDto,
                order.getCreated()
        );
    }

    private ShopOrderItemDto toItemDto(ShopOrderItem item) {

        ShopProductLiteDto productLiteDto = new ShopProductLiteDto(
                item.getShopProduct().getId(),
                item.getShopProduct().getName(),
                item.getShopProduct().getMeasurementName(),
                item.getShopProduct().getMeasurementUnit()
        );

        return new ShopOrderItemDto(
                productLiteDto,
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubtotal()
        );
    }

}
