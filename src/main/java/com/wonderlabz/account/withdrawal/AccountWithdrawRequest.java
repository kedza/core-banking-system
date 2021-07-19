package com.wonderlabz.account.withdrawal;

import com.wonderlabz.account.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountWithdrawRequest {

    private String customerId;
    private BigDecimal  amount;
    private AccountType accountType;
    private String accountNumber;

}
