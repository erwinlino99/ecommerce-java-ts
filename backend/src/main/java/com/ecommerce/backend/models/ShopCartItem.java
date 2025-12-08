package com.ecommerce.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_cart_item")
public class ShopCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shop_cart_id")
    private ShopCart shopCart;

    @ManyToOne
    @JoinColumn(name = "shop_product_id")
    private ShopProduct shopProduct;

    @Column
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "sub_total")
    private Double subTotal;

    public ShopCartItem() {

    }

    public Integer getId() {
        return this.id;
    }

    public Integer getShopCartId() {
        return this.shopCart.getId();
    }

    public Integer getShopProductId() {
        return this.shopProduct.getId();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Double getUnitPrice() {
        return this.unitPrice;
    }

    public Double getSubTotal() {
        return this.subTotal;
    }

}
