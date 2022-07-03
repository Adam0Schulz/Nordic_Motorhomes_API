package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.Employee;
import com.spring.nordic_motorhomes_apiimpl.Repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    // Dependencies
    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) { this.employeeRepo = employeeRepo; }


    // Save a employee
    public Employee save(Employee employee) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);

        return employeeRepo.save(employee);
    }

    // Get an employee
    public Optional<Employee> get(Long id) {
        return employeeRepo.findById(id);
    }

    // Get all employees
    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    // Get all employees that match search keyword
    public List<Employee> getAll(String keyword) {
        return employeeRepo
                .findAll()
                .stream().filter(e -> e
                        .toString().contains(keyword))
                .collect(Collectors.toList());
    }

    // Update a employee
    public Optional<Employee> update(Long id, Employee employee) {
        employee.setID(id);
        return !(employeeRepo.existsById(id))
                ? Optional.empty()
                : Optional.of(save(employee));
    }

    // Delete a employee
    public void delete(Long id) { employeeRepo.deleteById(id); }
}
