package com.wonderlabz.account.open;

import com.wonderlabz.account.service.AccountOpenService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("account")
@Slf4j
public class AccountOpenController {

    @Autowired
    private AccountOpenService accountOpenService;

    @PostMapping("open")
    @ApiOperation(value = "Account Openining  API", response = AccountOpenResponse.class)
    public AccountOpenResponse createBankAccount(@RequestBody AccountOpenRequest accountOpenRequest) {
        log.info("---Received  Account Opening  request {}----", accountOpenRequest);
        return accountOpenService.openAccount(accountOpenRequest);

    }
}
