package com.bnp.product.repository;

import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void contextLoads() {
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldFindAllProducts() {
        var allProducts = repository.findAll();
        assertThat(allProducts).hasSize(2);
    }

    @Test
    void shouldFindByProductCode() {
        Optional<ProductEntity> result = repository.findByProductCode("P001");
        assertThat(result).isPresent();
        assertThat(result.get().getProductDescription()).isEqualTo("Investment Fund");
        assertThat(result.get().getStatus()).isEqualTo("A");
    }

    @Test
    void shouldReturnEmptyIfProductCodeNotFound() {
        Optional<ProductEntity> result = repository.findByProductCode("FAKE");
        assertThat(result).isEmpty();
    }
}
