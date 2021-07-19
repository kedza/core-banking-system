package com.wonderlabz.account.service;

import com.wonderlabz.account.withdrawal.AccountWithdrawRequest;
import com.wonderlabz.account.withdrawal.AccountWithdrawResponse;

public interface AccountWithdrawService {

    AccountWithdrawResponse withdraw(AccountWithdrawRequest accountWithdrawRequest);
}
