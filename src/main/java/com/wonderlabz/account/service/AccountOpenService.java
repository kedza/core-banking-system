package com.wonderlabz.account.service;

import com.wonderlabz.account.open.AccountOpenRequest;
import com.wonderlabz.account.open.AccountOpenResponse;

public interface AccountOpenService {

   AccountOpenResponse openAccount(AccountOpenRequest accountOpenRequest);
}
