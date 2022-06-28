package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import com.spring.nordic_motorhomes_apiimpl.Service.SystemVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/variables")
public class SystemVariableController {

    // Dependencies
    private final SystemVariableService systemVariableService;

    @Autowired
    public SystemVariableController(SystemVariableService systemVariableService) {
        this.systemVariableService = systemVariableService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/variables
    @GetMapping
    public List<SystemVariable> getAll(@PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // GET http://localhost:7070/api/adam123/variables/5
    @GetMapping("/{id}")
    public SystemVariable get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // POST http://localhost:7070/api/adam123/variables
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody SystemVariable variable, @PathVariable(name = "key") String key) {
        // create
    }

    //PUT http://localhost:7070/api/adam123/variables/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody SystemVariable variable, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //update
    }

    // DELETE http://localhost:7070/api/adam123/variables/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        // delete
    }
}
