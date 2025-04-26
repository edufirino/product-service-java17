package com.bnp.manualtransaction.domain.dto;

import java.math.BigDecimal;

import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;

public class ManualTransactionResponse {
    private Integer month;
    private Integer year;
    private Integer entryNumber;
    private String productCode;
    private String productDescription;
    private String description;
    private BigDecimal amount;

    public ManualTransactionResponse() {
    }

    public ManualTransactionResponse(Integer month, Integer year, Integer entryNumber,
            String productCode, String productDescription, String description, BigDecimal amount) {
        this.month = month;
        this.year = year;
        this.entryNumber = entryNumber;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.description = description;
        this.amount = amount;
    }

    public static ManualTransactionResponse toResponseDTO(ManualTransactionEntity entity, String productDescription) {
        if (entity == null)
            return null;

        return new ManualTransactionResponse(
                entity.getId().getMonth(),
                entity.getId().getYear(),
                entity.getId().getEntryNumber(),
                entity.getProductCode(),
                productDescription,
                entity.getDescription(),
                entity.getAmount());
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}