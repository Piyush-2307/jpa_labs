package com.example.jap_labs.repository;

import com.example.jap_labs.dto.CustomerSummary;
import com.example.jap_labs.entity.Customer;
import com.example.jap_labs.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    @Query("""
SELECT c
 FROM Customer c
  WHERE c.gender = :gender
   AND LOWER(c.username)
    LIKE LOWER(CONCAT('%', :username,'%'))
""")
    List<Customer> findByGenderAndUsername(Gender gender, String username);

    @Query("""
SELECT new com.example.jap_labs.dto.CustomerSummary(
    c.username,
    c.email
)
FROM Customer c
WHERE c.gender = :gender
""")
    List<CustomerSummary> findUserSummaries(@Param("gender")Gender gender);

//    List<Customer> findByGenderAndUsernameContainingIgnoreCaseOrderByUsernameAsc(Gender gender, String username);
}
