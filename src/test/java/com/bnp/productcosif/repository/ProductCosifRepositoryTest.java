package com.bnp.productcosif.repository;

import com.bnp.productcosif.domain.entity.ProductCosifEntity;
import com.bnp.productcosif.domain.repository.ProductCosifRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest
@EntityScan(basePackages = "com.bnp")
class ProductCosifRepositoryTest {

    @Autowired
    private ProductCosifRepository repository;

    @Test
    void contextLoads() {
        assertThat(repository).isNotNull();
    }

    @Test
    void shouldFindAllProductCosifRecords() {
        List<ProductCosifEntity> all = repository.findAll();
        assertThat(all).hasSize(4);
    }

    @Test
    void shouldFindByProductCode() {
        List<ProductCosifEntity> results = repository.findByProductCode("P001");
        assertThat(results).hasSize(1);
        System.out.println("Retrieved CosifCode: " + results.get(0).getCosifCode());
        assertThat(results.get(0).getCosifCode().trim()).isEqualTo("COSIF123456");
    }

    @Test
    void shouldCheckIfProductCosifExistsByProductCodeAndCosifCode() {
        boolean exists = repository.existsByProductCodeAndCosifCode("P001", "COSIF123456");
        System.out.println("Existence check for P001 and COSIF123456: " + exists);
        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseIfProductCosifDoesNotExist() {
        boolean exists = repository.existsByProductCodeAndCosifCode("P999", "FAKECODE999");
        assertThat(exists).isFalse();
    }
}
