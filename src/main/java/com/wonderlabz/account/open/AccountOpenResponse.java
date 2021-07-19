package com.wonderlabz.account.open;

import lombok.Data;

@Data
public class AccountOpenResponse {
    private String accountNumber;
    private boolean success;
    private String message;
}
