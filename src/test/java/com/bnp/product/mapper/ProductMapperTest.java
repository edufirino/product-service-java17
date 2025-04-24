package com.bnp.product.mapper;

import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    @Test
    void shouldMapProductEntityToDTO() {
        ProductEntity entity = new ProductEntity();
        entity.setProductCode("P001");
        entity.setProductDescription("Investment Fund");
        entity.setStatus("A");

        ProductDTO dto = ProductMapper.toDTO(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getProductCode()).isEqualTo("P001");
        assertThat(dto.getProductDescription()).isEqualTo("Investment Fund");
        assertThat(dto.getStatus()).isEqualTo("A");
    }
}
