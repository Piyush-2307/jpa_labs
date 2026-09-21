package com.example.jap_labs.repository;

import com.example.jap_labs.dto.ActivityStatusProjection;
import com.example.jap_labs.dto.CustomerSummary;
import com.example.jap_labs.dto.GenderCountResponse;
import com.example.jap_labs.entity.Customer;
import com.example.jap_labs.enums.Activity;
import com.example.jap_labs.enums.Gender;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

//    @Query("""
//SELECT COUNT(c)
//FROM Customer c
//""")
//    Long countCustomers();

    @Query("""
SELECT new com.example.jap_labs.dto.GenderCountResponse(
    c.gender,
    COUNT(c)
)
FROM Customer c
GROUP BY c.gender
HAVING COUNT(*) >= 2
""")
    List<GenderCountResponse> countCustomerByGender();

    @Modifying(
            flushAutomatically = true,
            clearAutomatically = true
    )
    @Query("""
UPDATE Customer c
SET c.activity = :activity
WHERE c.gender = :gender
""")
 int offlineCustomers(Gender gender, Activity activity);

    @Query(value = """
        UPDATE customers
        SET activity = :activity
        WHERE id = :id
        RETURNING
                id AS id,
                email AS email,
                activity AS activity
""", nativeQuery = true)
    ActivityStatusProjection updateActivityStatusNative(@Param("id") Long id, @Param("activity") String activity);

    Slice<Customer> findBy(Pageable pageable);

//    List<Customer> findByGenderAndUsernameContainingIgnoreCaseOrderByUsernameAsc(Gender gender, String username);
}
