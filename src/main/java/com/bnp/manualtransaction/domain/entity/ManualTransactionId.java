package com.bnp.manualtransaction.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.io.Serial;


@Embeddable
public class ManualTransactionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "month", nullable = false)
    private final Integer month;

    @Column(name = "year", nullable = false)
    private final Integer year;

    @Column(name = "entry_number", nullable = false)
    private final Integer entryNumber;

    @SuppressWarnings("unused")
    protected ManualTransactionId() {
        this.month = null;
        this.year = null;
        this.entryNumber = null;
    }

    public ManualTransactionId(Integer month, Integer year, Integer entryNumber) {
        this.month = month;
        this.year = year;
        this.entryNumber = entryNumber;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManualTransactionId that)) return false;
        return Objects.equals(month, that.month) &&
                Objects.equals(year, that.year) &&
                Objects.equals(entryNumber, that.entryNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year, entryNumber);
    }
}
