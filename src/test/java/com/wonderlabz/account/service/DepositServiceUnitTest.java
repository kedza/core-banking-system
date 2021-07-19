package com.wonderlabz.account.service;

import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.deposit.AccountDepositResponse;
import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import com.wonderlabz.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;


public class DepositServiceUnitTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountDepositService accountDepositService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountDepositService = new AccountDepositServiceImpl(accountRepository);
    }

    @Test
    @DisplayName("deposit money into account")
    public void givenDepositRequest_whenDepositingMoney_shouldReturnSuccess(){
        given(accountRepository.findByAccountNumber(anyString()))
                .willReturn(Optional.of(TestData.getAccount2()));

        given(accountRepository.findByCustomerIdAndAccountType(anyString(),any(AccountType.class)))
                .willReturn(Optional.of(TestData.getAccount2()));
        given(accountRepository.save(any(Account.class))).willReturn(TestData.getAccount2());
        AccountDepositResponse response = accountDepositService.depositAccount(TestData.getDepositRequest2());
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).as("truthy").isTrue();
        assertThat(response.getMessage()).as("narrative").isEqualTo("Deposit success. New account balance :11000");
        //verify(accountRepository,times(2)).findByAccountNumber(anyString());
        //verify(accountRepository,times(1)).save(any(Account.class));
    }





}
