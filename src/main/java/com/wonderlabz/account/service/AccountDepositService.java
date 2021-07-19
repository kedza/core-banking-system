package com.wonderlabz.account.service;

import com.wonderlabz.account.deposit.AccountDepositRequest;
import com.wonderlabz.account.deposit.AccountDepositResponse;

public interface AccountDepositService {

    AccountDepositResponse depositAccount(AccountDepositRequest accountDepositRequest);
}
