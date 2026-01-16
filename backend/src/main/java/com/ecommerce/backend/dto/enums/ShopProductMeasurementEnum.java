package com.ecommerce.backend.dto.enums;

public enum ShopProductMeasurementEnum {
    // CREAMOS SOLO LOS ENUM SIN PARAMETROS
    UDS(1), CAJA(2), PALLET(3);

    private final Integer id;

    ShopProductMeasurementEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public static boolean isValid(String shopProductMeasurementName) {

        for (ShopProductMeasurementEnum me : ShopProductMeasurementEnum.values()) {
            if (me.name().equalsIgnoreCase(shopProductMeasurementName.trim())) {
                return true;
            }
        }
        return false;
    }

    public static Integer getId(String importBrand) {
        if (importBrand == null)
            return null;
        for (ShopProductMeasurementEnum me : ShopProductMeasurementEnum.values()) {
            if (me.name().equalsIgnoreCase(importBrand.trim())) {
                return me.id;
            }
        }
        return null;
    }
}
