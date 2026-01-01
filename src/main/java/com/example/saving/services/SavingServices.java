package com.example.saving.services;

import com.example.saving.dto.AccountValidationDTO;
import com.example.saving.dto.TransferDTO;
import com.example.saving.dto.TransferResponseDTO;
import com.example.saving.entity.Saving;
import com.example.saving.entity.TransactionHistory;
import com.example.saving.repository.SavingRepository;
import com.example.saving.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SavingServices {

    private final SavingRepository savingRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public SavingServices(SavingRepository savingRepository, TransactionHistoryRepository transactionHistoryRepository) {
        this.savingRepository = savingRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public List<Saving> getAllSaving() {
        return savingRepository.findAll();
    }

    public Optional<Saving> getSavingById(Long id) {
        return savingRepository.findById(id);
    }

    public Optional<Saving> getSavingByName(String name) {
        return savingRepository.findByName(name);
    }

    public Optional<Saving> getSavingByAccountNumber(String accountNumber) {
        return savingRepository.findByAccountNumber(accountNumber);
    }

    public Saving createSaving(Saving saving) {
        return savingRepository.save(saving);
    }

    public Saving updateSaving(Long id, Saving savingDetails) {
        Saving saving = savingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saving not found with id: " + id));

        saving.setName(savingDetails.getName());
        saving.setAccountNumber(savingDetails.getAccountNumber());
        saving.setBalance(savingDetails.getBalance());
        saving.setBankName(savingDetails.getBankName());
        saving.setBankLogo(savingDetails.getBankLogo());

        return savingRepository.save(saving);
    }

    public void deleteSaving(Long id) {
        savingRepository.deleteById(id);
    }

    public List<Saving> getSavingsByCustomerId(Long customerId) {
        return savingRepository.findByCustomerId(customerId);
    }

    // Validasi nomor rekening
    public AccountValidationDTO validateAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            return new AccountValidationDTO(false, null, null, null, null, "Nomor rekening tidak boleh kosong");
        }

        Optional<Saving> savingOpt = savingRepository.findByAccountNumber(accountNumber);

        if (savingOpt.isEmpty()) {
            return new AccountValidationDTO(false, accountNumber, null, null, null, "Nomor rekening tidak ditemukan");
        }

        Saving saving = savingOpt.get();
        return new AccountValidationDTO(
                true,
                saving.getAccountNumber(),
                saving.getCustomer().getName(),
                saving.getBankName(),
                saving.getBankLogo(),
                "Rekening ditemukan"
        );
    }

    @Transactional
    public TransferResponseDTO transfer(TransferDTO transferDTO) {
        // Validasi input
        if (transferDTO.getNominal() == null || transferDTO.getNominal() <= 0) {
            return new TransferResponseDTO(false, "Nominal transfer harus lebih dari 0", null, null);
        }

        if (transferDTO.getNomorRekeningPengirim() == null || transferDTO.getNomorRekeningPengirim().trim().isEmpty()) {
            return new TransferResponseDTO(false, "Nomor rekening pengirim tidak boleh kosong", null, null);
        }

        if (transferDTO.getNomorRekeningPenerima() == null || transferDTO.getNomorRekeningPenerima().trim().isEmpty()) {
            return new TransferResponseDTO(false, "Nomor rekening penerima tidak boleh kosong", null, null);
        }

        if (transferDTO.getNomorRekeningPengirim().equals(transferDTO.getNomorRekeningPenerima())) {
            return new TransferResponseDTO(false, "Nomor rekening pengirim dan penerima tidak boleh sama", null, null);
        }

        // Cari rekening pengirim
        Optional<Saving> pengirimOpt = savingRepository.findByAccountNumber(transferDTO.getNomorRekeningPengirim());
        if (pengirimOpt.isEmpty()) {
            return new TransferResponseDTO(false, "Rekening pengirim tidak ditemukan", null, null);
        }

        // Cari rekening penerima
        Optional<Saving> penerimaOpt = savingRepository.findByAccountNumber(transferDTO.getNomorRekeningPenerima());
        if (penerimaOpt.isEmpty()) {
            return new TransferResponseDTO(false, "Rekening penerima tidak ditemukan", null, null);
        }

        Saving pengirim = pengirimOpt.get();
        Saving penerima = penerimaOpt.get();

        // Cek saldo pengirim
        if (pengirim.getBalance() < transferDTO.getNominal()) {
            return new TransferResponseDTO(false, "Saldo tidak mencukupi", pengirim.getBalance(), penerima.getBalance());
        }

        // Proses transfer
        pengirim.setBalance(pengirim.getBalance() - transferDTO.getNominal());
        penerima.setBalance(penerima.getBalance() + transferDTO.getNominal());

        // Simpan perubahan
        savingRepository.save(pengirim);
        savingRepository.save(penerima);

        // Simpan history transaksi
        TransactionHistory history = new TransactionHistory(
                "TRANSFER",
                transferDTO.getNominal(),
                pengirim.getAccountNumber(),
                pengirim.getCustomer().getName(),
                pengirim.getBankName(),
                penerima.getAccountNumber(),
                penerima.getCustomer().getName(),
                penerima.getBankName(),
                transferDTO.getDescription(),
                "SUCCESS"
        );
        transactionHistoryRepository.save(history);

        return new TransferResponseDTO(
                true,
                "Transfer berhasil",
                pengirim.getBalance(),
                penerima.getBalance()
        );
    }

    // Get transaction history
    public List<TransactionHistory> getAllTransactionHistory() {
        return transactionHistoryRepository.findAll();
    }

    public List<TransactionHistory> getTransactionHistoryByAccount(String accountNumber) {
        return transactionHistoryRepository.findAllByAccountNumber(accountNumber);
    }

    public List<TransactionHistory> getRecentTransactions() {
        return transactionHistoryRepository.findTop10ByOrderByTransactionDateDesc();
    }
}