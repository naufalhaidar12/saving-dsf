package com.example.saving.dto;

public class AccountValidationDTO {
    private boolean valid;
    private String accountNumber;
    private String accountName;
    private String bankName;
    private String bankLogo;
    private String message;

    public AccountValidationDTO() {}

    public AccountValidationDTO(boolean valid, String accountNumber, String accountName,
                                String bankName, String bankLogo, String message) {
        this.valid = valid;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.bankName = bankName;
        this.bankLogo = bankLogo;
        this.message = message;
    }

    // Getters and Setters
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}