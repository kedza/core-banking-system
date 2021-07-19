package com.wonderlabz.account.service;

import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.open.AccountOpenRequest;
import com.wonderlabz.account.open.AccountOpenResponse;
import com.wonderlabz.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@Slf4j
public class AccountOpenServiceImpl implements AccountOpenService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountOpenServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountOpenResponse openAccount(AccountOpenRequest accountOpenRequest) {

        AccountOpenResponse accountOpenResponse = new AccountOpenResponse();

        if (accountOpenRequest.getAccountType() == null) {
            accountOpenResponse.setMessage("account type is null");
            accountOpenResponse.setSuccess(false);
            return accountOpenResponse;
        }

        if (accountOpenRequest.getCustomerId().equals(null)) {
            accountOpenResponse.setMessage("customer id is null");
            accountOpenResponse.setSuccess(false);
            return accountOpenResponse;
        }

        if (accountOpenRequest.getInitialDeposit().equals(null)) {
            accountOpenResponse.setMessage("minimum deposit required is null");
            accountOpenResponse.setSuccess(false);
            return accountOpenResponse;
        }


        Optional<Account> account = accountRepository.findByCustomerIdAndAccountType(accountOpenRequest.getCustomerId(), accountOpenRequest.getAccountType());
        if (account.isPresent()) {
            accountOpenResponse.setMessage("Account with account type " + account.get().getAccountType() + " with customer id :" + accountOpenRequest.getCustomerId()
                    + " already exist");
            accountOpenResponse.setSuccess(false);
            return accountOpenResponse;
        }

        if (accountOpenRequest.getInitialDeposit().doubleValue() < 1000 && accountOpenRequest.getAccountType().equals(AccountType.SAVINGS)) {
            accountOpenResponse.setSuccess(false);
            accountOpenResponse.setMessage("Minimum amount required to open savings account is  1000");
            //TODO make  minimum amount configurable.Hard coding values increases technical debt
            return accountOpenResponse;
        }

        Account $account = new Account();
        $account.setAccountType(accountOpenRequest.getAccountType());
        $account.setAccountNumber(getAccountNumbers().trim());//TODO create a microservice to generate account numbers
        $account.setCustomerId(accountOpenRequest.getCustomerId());
        $account.setCustomerFirstName(accountOpenRequest.getCustomerFirstName());
        $account.setCustomerLastName(accountOpenRequest.getCustomerLastName());
        accountOpenResponse.setAccountNumber($account.getAccountNumber());
        accountOpenResponse.setSuccess(true);
        accountOpenResponse.setMessage("Account successfully Opened!!");
        accountRepository.save($account);
        return accountOpenResponse;
    }


    private String getAccountNumbers() {

        String start = "BE";
        Random value = new Random();

        //Generate two values to append to 'BE'
        int r1 = value.nextInt(10);
        int r2 = value.nextInt(10);
        start += Integer.toString(r1) + Integer.toString(r2) + " ";

        int count = 0;
        int n = 0;
        for (int i = 0; i < 12; i++) {
            if (count == 4) {
                start += " ";
                count = 0;
            } else
                n = value.nextInt(10);
            start += Integer.toString(n);
            count++;

        }
        return start;
    }

}


