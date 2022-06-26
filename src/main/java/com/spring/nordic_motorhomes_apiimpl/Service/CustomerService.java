package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Adam
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Get a customer by driving licence number
    public Customer getCustomerByDLN(int num) {
        return customerRepository.findByDrivingLicenceNumber(num).orElse(null);
    }

    // Get a customer or create one and return it
    public Customer getOrCreateCustomer(int dln, String first, String last, int phone) {
        Customer customer = getCustomerByDLN(dln);
        if (customer == null) {
            customer = Customer.builder()
                    .drivingLicenceNumber(dln)
                    .firstName(first)
                    .lastName(last)
                    .phoneNumber(phone)
                    .build();

            customerRepository.save(customer);
        }

        return customer;
    }
}
