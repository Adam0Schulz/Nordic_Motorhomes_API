package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Customer;
import com.spring.nordic_motorhomes_apiimpl.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/customers")
public class CustomerController {

    // Dependencies
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/customers
    @GetMapping
    public List<Customer> getAll(@PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // GET http://localhost:7070/api/adam123/customers/5
    @GetMapping("/{id}")
    public Customer get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // POST http://localhost:7070/api/adam123/customers
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Customer customer, @PathVariable(name = "key") String key) {
        // create
    }

    //PUT http://localhost:7070/api/adam123/customers/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Customer customer, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //update
    }

    // DELETE http://localhost:7070/api/adam123/customers/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        // delete
    }
}
