package com.example.saving.dto;

public class TransferResponseDTO {
    private boolean success;
    private String message;
    private Double saldoPengirim;
    private Double saldoPenerima;

    public TransferResponseDTO() {
    }

    public TransferResponseDTO(boolean success, String message, Double saldoPengirim, Double saldoPenerima) {
        this.success = success;
        this.message = message;
        this.saldoPengirim = saldoPengirim;
        this.saldoPenerima = saldoPenerima;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getSaldoPengirim() {
        return saldoPengirim;
    }

    public void setSaldoPengirim(Double saldoPengirim) {
        this.saldoPengirim = saldoPengirim;
    }

    public Double getSaldoPenerima() {
        return saldoPenerima;
    }

    public void setSaldoPenerima(Double saldoPenerima) {
        this.saldoPenerima = saldoPenerima;
    }
}