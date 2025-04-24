package com.bnp.product.application.service;

import com.bnp.product.domain.entity.ProductEntity;
import com.bnp.product.domain.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DataSource dataSource;

    public ProductService(ProductRepository productRepository, DataSource dataSource) {
        this.productRepository = productRepository;
        this.dataSource = dataSource;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> findByProductCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    @PersistenceContext
    private EntityManager entityManager;
}
