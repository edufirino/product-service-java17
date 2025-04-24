package com.bnp.productcosif.mapper;

import com.bnp.productcosif.domain.entity.ProductCosifEntity;
import com.bnp.productcosif.domain.dto.ProductCosifDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCosifMapperTest {

    @Test
    void shouldMapEntityToDTO() {
        ProductCosifEntity entity = new ProductCosifEntity(
                "COSIF001", "PROD01", "CLASS01", "A"
        );

        ProductCosifDTO dto = ProductCosifMapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals("COSIF001", dto.getCosifCode());
        assertEquals("PROD01", dto.getProductCode());
        assertEquals("CLASS01", dto.getClassificationCode());
        assertEquals("A", dto.getStatus());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        ProductCosifDTO dto = ProductCosifMapper.toDTO(null);
        assertNull(dto);
    }
}
