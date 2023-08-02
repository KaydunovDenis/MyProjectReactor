package com.github.kaydunovdenis.accountservice.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public abstract class FinancialOperation {
    @Id
    UUID id;
    private String title;
    private BigDecimal amount;
    private Currency currency;
    private Date transactionTime;

    protected FinancialOperation() {}

    protected FinancialOperation(BigDecimal amount, Currency currency, Date transactionTime) {
        this.amount = amount;
        this.currency = currency;
        this.transactionTime = transactionTime;
    }

    protected FinancialOperation(UUID id, String title, BigDecimal amount, Currency currency, Date transactionTime) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.currency = currency;
        this.transactionTime = transactionTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
