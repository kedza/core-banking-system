package com.wonderlabz.account.withdrawal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.service.AccountWithdrawService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AccountWithdrawalController.class})
public class AccountWithdrawalControllerTest {
        @Autowired
        private MockMvc mockMvc;
        private ObjectMapper objectMapper;
        @MockBean
        private AccountWithdrawService accountWithdrawService;

        @BeforeEach
        void setUp() {
            objectMapper = new ObjectMapper();
        }

        @Test
        @DisplayName(" withdraw account test")
        void givenUserRequest_whenWithDrawAccount_shouldReturnSuccessResponse() throws Exception {
            final String url = "/account/withdraw";
            AccountWithdrawResponse response =  new AccountWithdrawResponse();
            response.setSuccess(true);
            response.setMessage("Account  withdraw success.New account balance : 3400");
            given(accountWithdrawService.withdraw(any(AccountWithdrawRequest.class))).willReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(TestData.getWithDrawalRequest())).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("success").value(true))
                    .andExpect(jsonPath("message").value("Account  withdraw success.New account balance : 3400"));
                    //.andExpect(jsonPath("accountNumber").value("1234"));
        }
    }

