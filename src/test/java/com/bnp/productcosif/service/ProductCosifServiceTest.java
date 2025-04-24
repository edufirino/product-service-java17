package com.bnp.productcosif.service;

import com.bnp.productcosif.application.service.ProductCosifService;
import com.bnp.productcosif.domain.entity.ProductCosifEntity;
import com.bnp.productcosif.domain.dto.ProductCosifDTO;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCosifServiceTest {

    @Mock
    private ProductCosifRepository repository;

    @InjectMocks
    private ProductCosifService service;

    @Test
    void shouldReturnAllProductCosifs() {
        ProductCosifEntity entity = new ProductCosifEntity();
        entity.setCosifCode("12345678901");
        entity.setProductCode("P001");
        entity.setClassificationCode("CL001");
        entity.setStatus("A");

        when(repository.findAll()).thenReturn(List.of(entity));

        List<ProductCosifDTO> result = service.getAllProductCosifs();

        assertEquals(1, result.size());
        ProductCosifDTO actualDto = result.get(0);
        assertEquals("12345678901", actualDto.getCosifCode());
        assertEquals("P001", actualDto.getProductCode());
        assertEquals("CL001", actualDto.getClassificationCode());
        assertEquals("A", actualDto.getStatus());

        verify(repository).findAll();
    }
}
