package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Employee;
import com.spring.nordic_motorhomes_apiimpl.Repository.EmployeeRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.GeneralRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService extends GeneralService<Employee> {

    @Autowired
    public EmployeeService(GeneralRepository<Employee> repo) {
        super(repo);
    }

    // Save a employee
    @Override
    public Employee save(Employee employee) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);

        return repo.save(employee);
    }

}
