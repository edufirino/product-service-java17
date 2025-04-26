package com.bnp.manualtransaction.domain.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ManualTransactionDTO {

    @NotNull(message = "Month is required")
    @Min(value = 1, message = "Month must be between 1 and 12")
    @Max(value = 12, message = "Month must be between 1 and 12")
    private Integer month;

    @NotNull(message = "Year is required")
    @Min(value = 1800, message = "Year must be no earlier than 1800")
    @Max(value = 3000, message = "Year must be no later than 3000")
    private Integer year;

    private Integer entryNumber;

    @NotBlank(message = "Product code is required")
    @Size(min = 4, max = 4, message = "Product code must be exactly 4 characters")
    private String productCode;

    @NotBlank(message = "Cosif code is required")
    @Size(min = 11, max = 11, message = "Cosif code must be exactly 11 characters")
    private String cosifCode;

    @NotBlank(message = "Description is required")
    @Size(max = 50, message = "Description must be at most 50 characters")
    private String description;

    private LocalDateTime transactionDate;

    @NotBlank(message = "User code is required")
    @Size(max = 15, message = "User code must be at most 15 characters")
    private String userCode;

    @NotNull(message = "Amount is required")
    @Digits(integer = 14, fraction = 2, message = "Amount must be a valid monetary value (up to 14 digits and 2 decimals)")
    private BigDecimal amount;

    @Size(max = 50, message = "Product description must be at most 255 characters")
    private String productDescription;

    public ManualTransactionDTO() {}

    public ManualTransactionDTO(
            Integer month,
            Integer year,
            Integer entryNumber,
            String productCode,
            String cosifCode,
            String description,
            LocalDateTime transactionDate,
            String userCode,
            BigDecimal amount
    ) {
        this.month = month;
        this.year = year;
        this.entryNumber = entryNumber;
        this.productCode = productCode;
        this.cosifCode = cosifCode;
        this.description = description;
        this.transactionDate = transactionDate;
        this.userCode = userCode;
        this.amount = amount;
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

    public String getCosifCode() {
        return cosifCode;
    }

    public void setCosifCode(String cosifCode) {
        this.cosifCode = cosifCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
