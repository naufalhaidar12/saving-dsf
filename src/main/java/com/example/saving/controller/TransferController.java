package com.example.saving.controller;

import com.example.saving.dto.AccountValidationDTO;
import com.example.saving.dto.TransferDTO;
import com.example.saving.dto.TransferResponseDTO;
import com.example.saving.entity.TransactionHistory;
import com.example.saving.services.SavingServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TransferController {

    private final SavingServices savingServices;

    public TransferController(SavingServices savingServices) {
        this.savingServices = savingServices;
    }

    // Validasi nomor rekening
    @GetMapping("/validate-account/{accountNumber}")
    public ResponseEntity<AccountValidationDTO> validateAccount(@PathVariable String accountNumber) {
        try {
            AccountValidationDTO validation = savingServices.validateAccount(accountNumber);
            return new ResponseEntity<>(validation, HttpStatus.OK);
        } catch (Exception e) {
            AccountValidationDTO errorResponse = new AccountValidationDTO(
                    false, null, null, null, null, "Terjadi kesalahan: " + e.getMessage()
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Transfer
    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseDTO> transfer(@RequestBody TransferDTO transferDTO) {
        try {
            TransferResponseDTO response = savingServices.transfer(transferDTO);

            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            TransferResponseDTO errorResponse = new TransferResponseDTO(
                    false,
                    "Terjadi kesalahan: " + e.getMessage(),
                    null,
                    null
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all transaction history
    @GetMapping("/transaction-history")
    public ResponseEntity<List<TransactionHistory>> getAllTransactionHistory() {
        try {
            List<TransactionHistory> history = savingServices.getAllTransactionHistory();
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get transaction history by account number
    @GetMapping("/transaction-history/account/{accountNumber}")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistoryByAccount(@PathVariable String accountNumber) {
        try {
            List<TransactionHistory> history = savingServices.getTransactionHistoryByAccount(accountNumber);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get recent transactions (last 10)
    @GetMapping("/transaction-history/recent")
    public ResponseEntity<List<TransactionHistory>> getRecentTransactions() {
        try {
            List<TransactionHistory> history = savingServices.getRecentTransactions();
            return new ResponseEntity<>(history, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}