package com.bnp.productcosif.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_cosif")
public class ProductCosifEntity {
    @Id
    @Column(name = "cosif_code", length = 11)
    private String cosifCode;

    @Column(name = "product_code", length = 4, nullable = false)
    private String productCode;

    @Column(name = "classification_code", length = 6)
    private String classificationCode;

    @Column(name = "status", length = 1)
    private String status;

    public ProductCosifEntity() {}

    public ProductCosifEntity(String cosifCode, String productCode, String classificationCode, String status) {
        this.cosifCode = cosifCode;
        this.productCode = productCode;
        this.classificationCode = classificationCode;
        this.status = status;
    }

    public String getCosifCode() {
        return cosifCode;
    }

    public void setCosifCode(String cosifCode) {
        this.cosifCode = cosifCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
