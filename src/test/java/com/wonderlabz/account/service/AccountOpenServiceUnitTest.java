package com.wonderlabz.account.service;

import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.open.AccountOpenRequest;
import com.wonderlabz.account.open.AccountOpenResponse;
import com.wonderlabz.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AccountOpenServiceUnitTest {

    private  AccountOpenService accountOpeningService;
    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountOpeningService =  new AccountOpenServiceImpl(accountRepository);
    }

   // @Test
    @DisplayName("creating user account")
    void givenUserRequest_whenCreatingAccount_shouldReturnSuccess() {
        given(accountRepository.save(any(Account.class))).willReturn(TestData.getAccount());
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.empty());
        AccountOpenResponse response = accountOpeningService.openAccount(TestData.getBankAccountRequest());
        assertThat(response).as("Account creation response").isNotNull();
        assertThat(response.isSuccess()).as("true").isTrue();
        assertThat(response.getAccountNumber()).as("accountNumber").isNotNull();
        assertThat(response.getMessage()).as("message").isEqualTo("Account successfully Opened!!");
        verify(accountRepository,times(1)).save(any(Account.class));
        verify(accountRepository,times(1)).findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }


    //@Test
    @DisplayName("attempt to create existing user account")
    void givenExistingUser_whenCreatingAccount_shouldReturnFailedResponse() {
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(TestData.getAccount()));
        AccountOpenResponse response = accountOpeningService.openAccount(TestData.getBankAccountRequest());
        assertThat(response).as("Account creation response").isNotNull();
        assertThat(response.isSuccess()).as("truthy").isFalse();
        //assertThat(response.getAccountNumber()).as("account number").isEqualTo("1234").;
        assertThat(response.getMessage()).as("message").isEqualTo("Account with account type SAVINGS with customer id :1234 already exist");
        verify(accountRepository,times(1)).findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }

    @Test
    @DisplayName("attempt to create saving account with no initial deposit user account")
    void givenUserRequest_whenCreatingSavingsAccount_shouldReturnFailedResponse() {
        AccountOpenRequest accountRequest = TestData.getBankAccountRequest();
        accountRequest.setInitialDeposit(new BigDecimal(0));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.empty());
        AccountOpenResponse response = accountOpeningService.openAccount(accountRequest);
        assertThat(response).as("Account creation response").isNotNull();
        assertThat(response.isSuccess()).as("truthy").isFalse();
        assertThat(response.getMessage()).as("narrative")
                .isEqualTo("Minimum amount required to open savings account is  1000");
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }


    //@Test
    @DisplayName("create current account")
    void givenUserRequest_whenCreatingCurrentAccount_shouldReturnSuccess(){
        AccountOpenRequest bankAccountRequest = TestData.getBankAccountRequest();
        bankAccountRequest.setAccountType(AccountType.CURRENT);
        bankAccountRequest.setInitialDeposit(BigDecimal.ONE);
        given(accountRepository.save(any(Account.class))).willReturn(TestData.getCurrentAccount());
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.empty());
        AccountOpenResponse response = accountOpeningService.openAccount(bankAccountRequest);
        assertThat(response).as("Account creation response").isNotNull();
        assertThat(response.isSuccess()).as("truthy").isTrue();
        assertThat(response.getAccountNumber()).as("accountNumber").isEqualTo("1");
        assertThat(response.getMessage()).as("narrative").isEqualTo("Account successfully created");
        verify(accountRepository,times(1)).save(any(Account.class));
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }

}
