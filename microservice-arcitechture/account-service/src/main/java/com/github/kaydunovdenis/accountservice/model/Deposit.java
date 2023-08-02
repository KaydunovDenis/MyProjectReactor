package com.github.kaydunovdenis.accountservice.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit extends FinancialOperation {
    private BigDecimal rate;
    private Date expirationDate;
    private boolean isCapitalized;
    private boolean isReplenished;
}
