package com.bnp.product.domain.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name = "product_code", length = 4)
    private String productCode;

    @Column(name = "product_desc", length = 30)
    private String productDescription;

    @Column(name = "status", length = 1)
    private String status;

    public ProductEntity() {}

    public ProductEntity(String productCode, String productDescription, String status) {
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.status = status;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
