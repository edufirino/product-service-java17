package com.bnp.productcosif.domain.repository;

import com.bnp.productcosif.domain.entity.ProductCosifEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductCosifRepository extends JpaRepository<ProductCosifEntity, String> {

    @NonNull
    List<ProductCosifEntity> findAll();

    List<ProductCosifEntity> findByProductCode(String productCode);

    boolean existsByProductCodeAndCosifCode(String productCode, String cosifCode);

}
