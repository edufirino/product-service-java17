package com.bnp.productcosif.domain.dto;

public class ProductCosifDTO {

    private final String cosifCode;
    private final String productCode;
    private final String classificationCode;
    private final String status;

    public ProductCosifDTO(String cosifCode, String productCode, String classificationCode, String status) {
        this.cosifCode = cosifCode;
        this.productCode = productCode;
        this.classificationCode = classificationCode;
        this.status = status;
    }

    public String getCosifCode() {
        return cosifCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public String getStatus() {
        return status;
    }
}
