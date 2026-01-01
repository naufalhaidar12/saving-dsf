package com.example.saving.repository;

import com.example.saving.entity.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    Optional<Saving> findByName(String name);
    Optional<Saving> findByAccountNumber(String accountNumber);
    List<Saving> findByCustomerId(Long customerId);
}