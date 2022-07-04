package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends GeneralService<Customer> {

    @Autowired
    public CustomerService(GeneralRepository<Customer> repo) {
        super(repo);
    }
}
