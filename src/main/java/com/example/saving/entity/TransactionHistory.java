package com.example.saving.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "transaction_type")
    private String transactionType;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, name = "sender_account_number")
    private String senderAccountNumber;

    @Column(nullable = false, name = "sender_name")
    private String senderName;

    @Column(nullable = false, name = "sender_bank")
    private String senderBank;

    @Column(nullable = false, name = "receiver_account_number")
    private String receiverAccountNumber;

    @Column(nullable = false, name = "receiver_name")
    private String receiverName;

    @Column(nullable = false, name = "receiver_bank")
    private String receiverBank;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }

    public TransactionHistory() {}

    public TransactionHistory(String transactionType, Double amount, String senderAccountNumber,
                              String senderName, String senderBank, String receiverAccountNumber,
                              String receiverName, String receiverBank, String description, String status) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.senderAccountNumber = senderAccountNumber;
        this.senderName = senderName;
        this.senderBank = senderBank;
        this.receiverAccountNumber = receiverAccountNumber;
        this.receiverName = receiverName;
        this.receiverBank = receiverBank;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderBank() {
        return senderBank;
    }

    public void setSenderBank(String senderBank) {
        this.senderBank = senderBank;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}