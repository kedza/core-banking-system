package com.wonderlabz.account.deposit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.service.AccountDepositService;
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

@WebMvcTest(controllers = {AccountDepositController.class})
public class AccountDepositControllerTest {
        @Autowired
        private MockMvc mockMvc;
        private ObjectMapper objectMapper;
        @MockBean
        private AccountDepositService accountDepositService;

        @BeforeEach
        void setUp() {
            objectMapper = new ObjectMapper();
        }

        @Test
        @DisplayName(" withdraw account test")
        void givenUserRequest_whenDepositIsPerfomed_shouldReturnSuccessResponse() throws Exception {
            final String url = "/account/deposit";
            AccountDepositResponse response =  new AccountDepositResponse();
            response.setSuccess(true);
            response.setMessage("Account  deposit success. New  account balance :3400");
            given(accountDepositService.depositAccount(any(AccountDepositRequest.class))).willReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(TestData.getDepositRequest())).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("success").value(true))
                    .andExpect(jsonPath("message").value("Account  deposit success. New  account balance :3400"));
                    //.andExpect(jsonPath("accountNumber").value("1234"));
        }
    }

