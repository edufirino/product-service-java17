package com.bnp.product.domain.dto;

public class ProductDTO {

    private String productCode;
    private String productDescription;
    private String status;

    public ProductDTO() {}

    public ProductDTO(String productCode, String productDescription, String status) {
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.status = status;
    }
    public String getProductCode() {
        return productCode;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public String getStatus() {
        return status;
    }
}
