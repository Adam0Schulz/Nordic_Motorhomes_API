package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    // Dependencies
    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    // Save a customer
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    // Get a customer
    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    // Get all customers
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

    // Update a customer
    public Optional<Customer> update(Long id, Customer customer) {
        customer.setID(id);
        return !(customerRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(customer));
    }

    // Delete a customer
    public void delete(Long id) { customerRepo.deleteById(id); }
}
