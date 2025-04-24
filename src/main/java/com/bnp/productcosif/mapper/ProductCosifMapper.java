package com.bnp.productcosif.mapper;

import com.bnp.productcosif.domain.entity.ProductCosifEntity;
import com.bnp.productcosif.domain.dto.ProductCosifDTO;

public class ProductCosifMapper {

    public static ProductCosifDTO toDTO(ProductCosifEntity entity) {
        if (entity == null) return null;

        return new ProductCosifDTO(
                entity.getCosifCode(),
                entity.getProductCode(),
                entity.getClassificationCode(),
                entity.getStatus()
        );
    }
}
