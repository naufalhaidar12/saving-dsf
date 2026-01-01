package com.example.saving.repository;

import com.example.saving.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByGovernmentId(String governmentId);
    Optional<Customer> findByEmail(String email);
}