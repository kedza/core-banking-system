package com.wonderlabz.account.service;

import com.wonderlabz.account.deposit.AccountDepositRequest;
import com.wonderlabz.account.deposit.AccountDepositResponse;
import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class AccountDepositServiceImpl implements AccountDepositService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountDepositServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDepositResponse depositAccount(AccountDepositRequest accountDepositRequest) {

        AccountDepositResponse accountDepositResponse = new AccountDepositResponse();

        if (accountDepositRequest.getAccountType().equals(null)) {

            accountDepositResponse.setMessage("Account Type is empty");
            accountDepositResponse.setSuccess(false);
            return accountDepositResponse;
        }


        if (accountDepositRequest.getAmount().equals(null)) {
            accountDepositResponse.setMessage("Amount is empty");
            accountDepositResponse.setSuccess(false);
            return accountDepositResponse;
        }


        if (accountDepositRequest.getCustomerId().equals(null)) {
            accountDepositResponse.setMessage("Customer Id is empty");
            accountDepositResponse.setSuccess(false);
            return accountDepositResponse;
        }


        Optional<Account> account = accountRepository.findByCustomerIdAndAccountType(accountDepositRequest.getCustomerId(), accountDepositRequest.getAccountType());
        if (account.isPresent()) {


            Account a = account.get();


            if (a.getBalance() == null) {

                a.setBalance(BigDecimal.ZERO);
            }
            BigDecimal balance = a.getBalance().add(accountDepositRequest.getAmount());
            a.setBalance(balance);
            accountRepository.save(a);
            accountDepositResponse.setSuccess(true);
            accountDepositResponse.setMessage("Deposit success. New account balance :" + balance);
            return accountDepositResponse;


        } else {
            accountDepositResponse.setMessage("Account  with customer id " + accountDepositRequest.getCustomerId() + "could not be found");
            accountDepositResponse.setSuccess(false);
            return accountDepositResponse;
        }
    }
}
