package com.wonderlabz.account.model;

import lombok.Data;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Entity
@Data
public class TransactionHistory extends  BaseEntity {

    private String accountType;
    private String accountNumber;
    private String transactionType;
    private String amount;
    private LocalDateTime dateTime;
    private String operation;
    private String responseMessage;
    private boolean success;
}
