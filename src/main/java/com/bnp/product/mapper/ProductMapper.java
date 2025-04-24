package com.bnp.product.mapper;

import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) return null;

        return new ProductDTO(
                entity.getProductCode(),
                entity.getProductDescription(),
                entity.getStatus()
        );
    }
}
