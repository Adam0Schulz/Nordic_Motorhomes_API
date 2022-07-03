package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Employee;
import com.spring.nordic_motorhomes_apiimpl.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/employees")
public class EmployeeController {

    // Dependencies
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/employees
    @GetMapping
    public List<Employee> getAll(@PathVariable(name = "key") String key) {
        return employeeService.getAll();
    }

    // GET http://localhost:7070/api/adam123/employees/5
    @GetMapping("/{id}")
    public Employee get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        return employeeService.get(id).orElse(null);
    }

    // POST http://localhost:7070/api/adam123/employees
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Employee employee, @PathVariable(name = "key") String key) {
        employeeService.save(employee);
    }

    //PUT http://localhost:7070/api/adam123/employees/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Employee employee, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        employeeService.update(id, employee);
    }

    // DELETE http://localhost:7070/api/adam123/employees/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        employeeService.delete(id);
    }

}
