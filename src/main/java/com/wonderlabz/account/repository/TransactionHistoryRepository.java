package com.wonderlabz.account.repository;

import com.wonderlabz.account.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
}
