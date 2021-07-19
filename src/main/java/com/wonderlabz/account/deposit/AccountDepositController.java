package com.wonderlabz.account.deposit;

import com.wonderlabz.account.service.AccountDepositService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("account")
@Slf4j
public class AccountDepositController {

    @Autowired
    private AccountDepositService accountDepositService;

    @PostMapping("deposit")
    @ApiOperation(value = "Account Deposit  API", response = AccountDepositResponse.class)
    public AccountDepositResponse createBankAccount(@RequestBody AccountDepositRequest accountDepositRequest) {
        log.info("---Received  Account Deposit  request {}----", accountDepositRequest);
        return accountDepositService.depositAccount(accountDepositRequest);

    }
}
