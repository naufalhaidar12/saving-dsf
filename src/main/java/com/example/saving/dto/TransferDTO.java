package com.example.saving.dto;

public class TransferDTO {
    private Double nominal;
    private String nomorRekeningPengirim;
    private String nomorRekeningPenerima;
    private String description;

    public TransferDTO() {
    }

    public TransferDTO(Double nominal, String nomorRekeningPengirim, String nomorRekeningPenerima, String description) {
        this.nominal = nominal;
        this.nomorRekeningPengirim = nomorRekeningPengirim;
        this.nomorRekeningPenerima = nomorRekeningPenerima;
        this.description = description;
    }

    // Getters and Setters
    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public String getNomorRekeningPengirim() {
        return nomorRekeningPengirim;
    }

    public void setNomorRekeningPengirim(String nomorRekeningPengirim) {
        this.nomorRekeningPengirim = nomorRekeningPengirim;
    }

    public String getNomorRekeningPenerima() {
        return nomorRekeningPenerima;
    }

    public void setNomorRekeningPenerima(String nomorRekeningPenerima) {
        this.nomorRekeningPenerima = nomorRekeningPenerima;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}