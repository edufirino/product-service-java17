package com.bnp.manualtransaction.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "manual_transaction")
public class ManualTransactionEntity {

    @EmbeddedId
    private ManualTransactionId id;

    @NotNull
    @Column(name = "product_code", length = 4)
    private String productCode;

    @NotNull
    @Column(name = "cosif_code", length = 11)
    private String cosifCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @NotNull
    @Size(max = 15)
    @Column(name = "user_code")
    private String userCode;

    @NotNull
    @Min(0)
    @Column(name = "amount", precision = 16, scale = 2)
    private BigDecimal amount;

    public ManualTransactionEntity() {
    }

    public ManualTransactionEntity(
            ManualTransactionId id,
            String productCode,
            String cosifCode,
            String description,
            LocalDateTime transactionDate,
            String userCode,
            BigDecimal amount) {
        this.id = id;
        this.productCode = productCode;
        this.cosifCode = cosifCode;
        this.description = description;
        this.transactionDate = transactionDate;
        this.userCode = userCode;
        this.amount = amount;
    }

    public ManualTransactionId getId() {
        return id;
    }

    public void setId(ManualTransactionId id) {
        this.id = id;
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
}
