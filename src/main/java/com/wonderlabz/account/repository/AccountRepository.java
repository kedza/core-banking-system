package com.wonderlabz.account.repository;

import com.wonderlabz.account.model.Account;
import com.wonderlabz.account.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerIdAndAccountType(String customerId, AccountType accountType);
    Optional<Account> findByAccountNumber(String accountNumber);
}
