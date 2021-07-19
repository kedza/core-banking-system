package com.wonderlabz.account.data;

import com.wonderlabz.account.deposit.AccountDepositRequest;
import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.open.AccountOpenRequest;
import com.wonderlabz.account.withdrawal.AccountWithdrawRequest;

import java.math.BigDecimal;


public class TestData {

    public static AccountOpenRequest getBankAccountRequest() {
        AccountOpenRequest bankAccountRequest = new AccountOpenRequest();
        bankAccountRequest.setAccountType(AccountType.SAVINGS);
        bankAccountRequest.setCustomerLastName("Goredema");
        bankAccountRequest.setCustomerFirstName("Kelvin");
        bankAccountRequest.setCustomerId("1234");
        bankAccountRequest.setInitialDeposit(new BigDecimal(10000));
        bankAccountRequest.setAccountNumber("1234");
        return bankAccountRequest;
    }

    /*public static Account getAccount() {
        final Account newAccount = new Account();
        newAccount.setAccountType(AccountType.SAVINGS);
        newAccount.setId(1L);
        newAccount.setBalance(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1");
        newAccount.setName("evans");
        newAccount.setSurname("chikuni");
        newAccount.setDepositReferenceNumber("1234");
        newAccount.setInitialDeposit(new BigDecimal(10000.00));
        return newAccount;
    }*/

    public static AccountDepositRequest getDepositRequest() {
        final AccountDepositRequest depositRequest = new AccountDepositRequest();
        depositRequest.setAmount(new BigDecimal(1000.00));
        depositRequest.setCustomerId("1234");
        depositRequest.setAccountType(AccountType.CURRENT);
        return depositRequest;
    }


    public static AccountDepositRequest getDepositRequest2() {
        final AccountDepositRequest depositRequest = new AccountDepositRequest();
        depositRequest.setAmount(new BigDecimal(1000.00));
        depositRequest.setCustomerId("1234");
        depositRequest.setAccountNumber("1234");
        depositRequest.setAccountType(AccountType.SAVINGS);
        return depositRequest;
    }

    public static AccountWithdrawRequest getWithDrawalRequest() {
        final AccountWithdrawRequest withdrawalRequest = new AccountWithdrawRequest();
        withdrawalRequest.setAmount(new BigDecimal(10000));
        withdrawalRequest.setAccountType(AccountType.SAVINGS);
        withdrawalRequest.setAccountNumber("1234");
        withdrawalRequest.setCustomerId("1234");
        return withdrawalRequest;
    }


    public static Account getAccount() {
        final Account newAccount = new Account();
        newAccount.setAccountType(AccountType.SAVINGS);
        newAccount.setId(1L);
        newAccount.setBalance(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1");
        newAccount.setCustomerFirstName("Kelvin");
        newAccount.setCustomerLastName("Goredema");
        newAccount.setInitialDeposit(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1234");
        newAccount.setCustomerId("1234");
        return newAccount;
    }

    public static Account getAccount2() {
        final Account newAccount = new Account();
        newAccount.setAccountType(AccountType.SAVINGS);
        newAccount.setBalance(new BigDecimal(10000.00));
        newAccount.setCustomerFirstName("Kelvin");
        newAccount.setCustomerLastName("Goredema");
        newAccount.setInitialDeposit(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1234");
        newAccount.setCustomerId("1234");
        return newAccount;
    }

    public static Account getSavingsAccount() {
        final Account newAccount = new Account();
        newAccount.setAccountType(AccountType.SAVINGS);
        newAccount.setId(1L);
        newAccount.setBalance(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1");
        newAccount.setCustomerFirstName("Kelvin");
        newAccount.setCustomerLastName("Goredema");
        newAccount.setInitialDeposit(new BigDecimal(10000.00));
        newAccount.setAccountNumber("1234");
        return newAccount;
    }

    public static Account getCurrentAccount() {
        final Account newAccount = new Account();
        newAccount.setAccountType(AccountType.CURRENT);
        newAccount.setId(1L);
        newAccount.setBalance(new BigDecimal(200));
        newAccount.setAccountNumber("1");
        newAccount.setCustomerFirstName("Kelvin");
        newAccount.setCustomerLastName("Goredema");
        newAccount.setInitialDeposit(new BigDecimal(200));
        newAccount.setAccountNumber("1234");
        return newAccount;
    }
}
