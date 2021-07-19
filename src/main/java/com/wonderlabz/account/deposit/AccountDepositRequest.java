package com.wonderlabz.account.deposit;

import com.wonderlabz.account.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDepositRequest {

    private String customerId;
    private BigDecimal  amount;
    private AccountType accountType;
    private String accountNumber;

}
