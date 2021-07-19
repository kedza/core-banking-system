package com.wonderlabz.account.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
@Data
public class Account extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    private String accountNumber;

    //TODO create class to hold account amounts
    private BigDecimal openingBalance;
    private BigDecimal balance;
    private BigDecimal closingBalance;
    private BigDecimal initialDeposit;


    //TODO create class for customer information
    private String customerId;
    private String customerFirstName;
    private String customerLastName;



}
