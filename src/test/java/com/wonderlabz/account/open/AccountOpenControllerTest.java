package com.wonderlabz.account.open;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonderlabz.account.data.TestData;
import com.wonderlabz.account.service.AccountOpenService;
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

@WebMvcTest(controllers = {AccountOpenController.class})
public class AccountOpenControllerTest {
        @Autowired
        private MockMvc mockMvc;
        private ObjectMapper objectMapper;
        @MockBean
        private AccountOpenService accountOpeningService;

        @BeforeEach
        void setUp() {
            objectMapper = new ObjectMapper();
        }

        @Test
        @DisplayName("account opening test")
        void givenUserRequest_whenCreateBankAccount_shouldReturnSuccessResponse() throws Exception {
            final String url = "/account/open";
            final AccountOpenResponse response =  new AccountOpenResponse();
            response.setSuccess(true);
            response.setAccountNumber("1234");
            response.setMessage("Account successfully created");
            given(accountOpeningService.openAccount(any(AccountOpenRequest.class))).willReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(TestData.getBankAccountRequest())).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("success").value(true))
                    .andExpect(jsonPath("message").value("Account successfully created"))
                    .andExpect(jsonPath("accountNumber").value("1234"));
        }
    }

