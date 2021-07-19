package com.wonderlabz.account.withdrawal;

import com.wonderlabz.account.service.AccountWithdrawService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("account")
@Slf4j
public class AccountWithdrawalController {

    @Autowired
    private AccountWithdrawService accountWithdrawService;

    @PostMapping("withdraw")
    @ApiOperation(value = "Account Withdraw  API", response = AccountWithdrawResponse.class)
    public AccountWithdrawResponse createBankAccount(@RequestBody AccountWithdrawRequest accountOpenRequest) {
        log.info("---Received  Account Withdraw  request {}----", accountOpenRequest);
        return accountWithdrawService.withdraw(accountOpenRequest);

    }
}
