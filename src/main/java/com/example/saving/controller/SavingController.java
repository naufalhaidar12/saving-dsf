package com.example.saving.controller;

import com.example.saving.entity.Saving;
import com.example.saving.services.SavingServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saving")
@CrossOrigin(origins = "*")
public class SavingController {

    private final SavingServices savingServices;

    public SavingController(SavingServices savingServices) {
        this.savingServices = savingServices;
    }

    @GetMapping
    public ResponseEntity<List<Saving>> getAllSaving() {
        try {
            List<Saving> savings = savingServices.getAllSaving();
            return new ResponseEntity<>(savings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saving> getSavingById(@PathVariable Long id) {
        try {
            return savingServices.getSavingById(id)
                    .map(saving -> new ResponseEntity<>(saving, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Saving> getSavingByAccountNumber(@PathVariable String accountNumber) {
        try {
            return savingServices.getSavingByAccountNumber(accountNumber)
                    .map(saving -> new ResponseEntity<>(saving, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Saving> createSaving(@RequestBody Saving saving) {
        try {
            Saving createdSaving = savingServices.createSaving(saving);
            return new ResponseEntity<>(createdSaving, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Saving> updateSaving(@PathVariable Long id, @RequestBody Saving saving) {
        try {
            Saving updatedSaving = savingServices.updateSaving(id, saving);
            return new ResponseEntity<>(updatedSaving, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSaving(@PathVariable Long id) {
        try {
            savingServices.deleteSaving(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Saving>> getSavingsByCustomerId(@PathVariable Long customerId) {
        try {
            List<Saving> savings = savingServices.getSavingsByCustomerId(customerId);
            return new ResponseEntity<>(savings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}