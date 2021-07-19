package com.wonderlabz.account.service;

import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.repository.AccountRepository;
import com.wonderlabz.account.withdrawal.AccountWithdrawRequest;
import com.wonderlabz.account.withdrawal.AccountWithdrawResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountWithdrawServiceImpl implements AccountWithdrawService {


    private AccountRepository accountRepository;

    public AccountWithdrawServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountWithdrawResponse withdraw(AccountWithdrawRequest accountWithdrawRequest) {


        AccountWithdrawResponse accountWithdrawResponse = new AccountWithdrawResponse();

        if (accountWithdrawRequest.getCustomerId().equals(null)) {

            accountWithdrawResponse.setSuccess(false);
            accountWithdrawResponse.setMessage("Customer id is empty");
            return accountWithdrawResponse;
        }

        if (accountWithdrawRequest.getAccountType().equals(null)) {
            accountWithdrawResponse.setSuccess(false);
            accountWithdrawResponse.setMessage("Account Type  is empty");
            return accountWithdrawResponse;

        }

        if (accountWithdrawRequest.getAmount().equals(null)) {
            accountWithdrawResponse.setSuccess(false);
            accountWithdrawResponse.setMessage("Amount  is empty");
            return accountWithdrawResponse;

        }


        Optional<Account> account = accountRepository.findByCustomerIdAndAccountType(accountWithdrawRequest.getCustomerId(), accountWithdrawRequest.getAccountType());
        if (account.isPresent()) {


            Account $account = account.get();


            if ($account.getAccountType().equals(AccountType.SAVINGS) && $account.getBalance().subtract(accountWithdrawRequest.getAmount()).doubleValue() < 1000) {
                accountWithdrawResponse.setSuccess(false);
                accountWithdrawResponse.setMessage("Cannot withdrawal minimum balance");
                return accountWithdrawResponse;

            }


            BigDecimal overdraft = $account.getBalance().subtract(accountWithdrawRequest.getAmount()).multiply(new BigDecimal(-1));

            int status= overdraft.compareTo(new BigDecimal(100000));
            if ((status>0)&& ($account.getAccountType().equals(AccountType.CURRENT))) {
                accountWithdrawResponse.setSuccess(false);
                accountWithdrawResponse.setMessage("Account Limit exceed 100 000 .");
                return accountWithdrawResponse;

            }


            BigDecimal remaining = $account.getBalance().subtract(accountWithdrawRequest.getAmount());
            $account.setBalance(remaining);
            accountRepository.save($account);
            accountWithdrawResponse.setMessage("Account withdrawal success. Remaining amount is "+remaining);
            accountWithdrawResponse.setSuccess(true);
            return accountWithdrawResponse;


        } else {

            accountWithdrawResponse.setMessage("Account  with customer id " + accountWithdrawRequest.getCustomerId() + "could not be found");
            accountWithdrawResponse.setSuccess(false);
            return accountWithdrawResponse;
        }


    }
}
