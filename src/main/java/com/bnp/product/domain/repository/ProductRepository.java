package com.bnp.product.domain.repository;

import com.bnp.product.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public Optional<ProductEntity> findByProductCode(String productCode);

}
