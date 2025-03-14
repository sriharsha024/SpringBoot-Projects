package com.airline.Airline.Management.System.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.Airline.Management.System.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    
}
