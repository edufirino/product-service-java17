package com.bnp.product.service;

import com.bnp.product.application.service.ProductService;
import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        ProductEntity product1 = new ProductEntity("PR01", "Product 1", "A");
        ProductEntity product2 = new ProductEntity("PR02", "Product 2", "A");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductEntity> result = productService.findAll();

        assertThat(result).containsExactly(product1, product2);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductByCode_shouldReturnMatchingProduct() {
        ProductEntity product = new ProductEntity("PR01", "Product 1", "A");
        when(productRepository.findByProductCode("PR01")).thenReturn(Optional.of(product));

        Optional<ProductEntity> result = productService.findByProductCode("PR01");

        assertThat(result).isPresent().contains(product);
        verify(productRepository, times(1)).findByProductCode("PR01");
    }

    @Test
    void getProductByCode_shouldReturnEmptyWhenNotFound() {
        when(productRepository.findByProductCode("PRXX")).thenReturn(Optional.empty());

        Optional<ProductEntity> result = productService.findByProductCode("PRXX");

        assertThat(result).isEmpty();
        verify(productRepository, times(1)).findByProductCode("PRXX");
    }
}
