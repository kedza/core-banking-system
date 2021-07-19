package com.wonderlabz.account.service;

import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.repository.AccountRepository;
import com.wonderlabz.account.withdrawal.AccountWithdrawRequest;
import com.wonderlabz.account.withdrawal.AccountWithdrawResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static com.wonderlabz.account.data.TestData.getWithDrawalRequest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class WithdrawalServiceUnitTest {
    @Mock
    private AccountRepository accountRepository;
    private AccountWithdrawService withdrawalService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        withdrawalService = new AccountWithdrawServiceImpl(accountRepository);
    }

    @Test
    void attemptToWithDrawalWithNonExistingAccount() {
        given(accountRepository.findByCustomerIdAndAccountType(anyString(), any(AccountType.class)))
                .willReturn(Optional.empty());
        AccountWithdrawResponse response = withdrawalService.withdraw(TestData.getWithDrawalRequest());
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Account  with customer id 1234could not be found");
        verify(accountRepository, times(1))
                .findByCustomerIdAndAccountType(anyString(), any(AccountType.class));
    }

   @Test
    void withDrawalFromSavingsAccountExcedingRemainingBalance(){
        AccountWithdrawRequest withDrawalRequest = TestData.getWithDrawalRequest();
        withDrawalRequest.setAmount(new BigDecimal(9500.00));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(TestData.getAccount()));
        AccountWithdrawResponse response = withdrawalService.withdraw(withDrawalRequest);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Cannot withdrawal minimum balance");
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }


    @Test
    void withDrawalAmounSuccessfully(){
        AccountWithdrawRequest withDrawalRequest = TestData.getWithDrawalRequest();
        withDrawalRequest.setAmount(new BigDecimal(800.00));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(TestData.getAccount()));
        given(accountRepository.save(any(Account.class))).willReturn(TestData.getAccount());
        AccountWithdrawResponse  response = withdrawalService.withdraw(withDrawalRequest);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Account withdrawal success. Remaining amount is 9200");
        verify(accountRepository,times(1)).findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }
    @Test
    void withdrawalFromCurrentAccountSuccessfully(){
        Account account = TestData.getAccount2();
        account.setAccountType(AccountType.CURRENT);
        AccountWithdrawRequest withDrawalRequest = TestData.getWithDrawalRequest();
        withDrawalRequest.setAccountNumber("current");
        withDrawalRequest.setAmount(new BigDecimal(500.00));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(account));
        given(accountRepository.save(any(Account.class))).willReturn((account));
        AccountWithdrawResponse response = withdrawalService.withdraw(withDrawalRequest);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Account withdrawal success. Remaining amount is 9500");
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }

    @Test
    void attemptTowithdrawalFromCurrentAccountWithAmountGreaterThanOverdraftSuccessfully(){
        AccountWithdrawRequest withDrawalRequest = getWithDrawalRequest();
        withDrawalRequest.setAccountType(AccountType.CURRENT);
        withDrawalRequest.setAmount(new BigDecimal(350000.00));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(TestData.getAccount()));
        given(accountRepository.save(any(Account.class))).willReturn(TestData.getAccount());
        AccountWithdrawResponse response = withdrawalService.withdraw(withDrawalRequest);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Cannot withdrawal minimum balance");
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }


    @Test
    void attemptToDoAnOverdraftSuccessfully(){
        Account account = TestData.getAccount2();
        account.setBalance(BigDecimal.valueOf(100.00));
        account.setAccountType(AccountType.CURRENT);
        AccountWithdrawRequest withDrawalRequest = TestData.getWithDrawalRequest();
        withDrawalRequest.setAccountType(AccountType.CURRENT);
        withDrawalRequest.setAmount(new BigDecimal(1000.00));
        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(account));
        given(accountRepository.save(any(Account.class))).willReturn(account);
        AccountWithdrawResponse response = withdrawalService.withdraw(withDrawalRequest);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Money successfully withdrawn");
        verify(accountRepository,times(1))
                .findByCustomerIdAndAccountType(anyString(),any(AccountType.class));
    }






}
