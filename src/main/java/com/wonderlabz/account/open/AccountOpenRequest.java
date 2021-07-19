package com.wonderlabz.account.open;

import com.wonderlabz.account.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountOpenRequest {

    private String customerId;
    private String customerFirstName;
    private String customerLastName;
    private BigDecimal initialDeposit;
    private AccountType accountType;
    private  String accountNumber;

}
