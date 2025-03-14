package com.airline.Airline.Management.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.Airline.Management.System.Repo.CustomerRepo;
import com.airline.Airline.Management.System.entity.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepo.findById(id).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setNationality(customerDetails.getNationality());
            customer.setAadharNumber(customerDetails.getAadharNumber());
            customer.setAddress(customerDetails.getAddress());
            customer.setGender(customerDetails.getGender());
            customer.setPhoneNumber(customerDetails.getPhoneNumber());
            customer.setEmail(customerDetails.getEmail());
            return customerRepo.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }
}
