package com.spring.nordic_motorhomes_apiimpl.Repository;

import com.spring.nordicmotorhomes.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Wanesa
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByDrivingLicenceNumber(int num);
}
