package com.example.jap_labs.repository;

import com.example.jap_labs.entity.Customer;
import com.example.jap_labs.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    List<Customer> findByGenderAndUsernameContainingIgnoreCaseOrderByUsernameAsc(Gender gender, String username);
}
