package com.example.saving.repository;

import com.example.saving.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    // Cari transaksi berdasarkan sender account
    List<TransactionHistory> findBySenderAccountNumberOrderByTransactionDateDesc(String accountNumber);

    // Cari transaksi berdasarkan receiver account
    List<TransactionHistory> findByReceiverAccountNumberOrderByTransactionDateDesc(String accountNumber);

    // Cari semua transaksi yang melibatkan account tertentu (sebagai sender atau receiver)
    @Query("SELECT t FROM TransactionHistory t WHERE t.senderAccountNumber = :accountNumber OR t.receiverAccountNumber = :accountNumber ORDER BY t.transactionDate DESC")
    List<TransactionHistory> findAllByAccountNumber(@Param("accountNumber") String accountNumber);

    // Cari transaksi terbaru (limit 10)
    List<TransactionHistory> findTop10ByOrderByTransactionDateDesc();
}