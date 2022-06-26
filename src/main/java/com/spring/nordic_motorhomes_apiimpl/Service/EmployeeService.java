package com.spring.nordic_motorhomes_apiimpl.Service;


import com.spring.nordic_motorhomes_apiimpl.Entity.Employee;
import com.spring.nordic_motorhomes_apiimpl.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Adam
@Service
public class EmployeeService {

    // static attribute to keep track of who is logged in
    private static Employee currentEmp;

    // Set current employee
    public static void setCurrentEmp(Employee emp) {
        currentEmp = emp;
    }

    // Get current employee
    public static Employee getCurrentEmp() {
        return currentEmp;
    }



    @Autowired
    private EmployeeRepository employeeRepository;

    // Creating password encoder for hashing password
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Login - checks if the employee exists, validates password and returns the employee
    public Employee login(String username, String password) {

        Employee employee = null;
        ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();

        for (Employee emp : employees) {
            if(emp.getEmail().equals(username) && emp.getPassword().equals(password)) {
                employee = emp;
                break;
            }
        }

        return employee;
    }

    // Get employee by id
    public Employee getEmpById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Create employee
    public Employee createEmp(int cpr, String email, String first, String last, String password, int phone, String title) {
        Employee newEmployee = Employee.builder()
                .CPR(cpr)
                .email(email)
                .firstName(first)
                .lastName(last)
                .password(password)
                .phoneNumber(phone)
                .title(title)
                .build();
        save(newEmployee);
        return newEmployee;
    }

    // Save - hashes the password before it saves the employee
    public Employee save(Employee employee) {
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeRepository.save(employee);
    }
}
