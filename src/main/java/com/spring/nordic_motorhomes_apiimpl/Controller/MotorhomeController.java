package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Motorhome;
import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Service.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/motorhomes")
public class MotorhomeController {

    //Dependencies
    private final MotorhomeService motorhomeService;

    @Autowired
    public MotorhomeController(MotorhomeService motorhomeService) {
        this.motorhomeService = motorhomeService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/motorhomes/12254/status
    @GetMapping("/{id}/status")
    public Status getStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        return motorhomeService.getStatus(id);
    }

    // GET http://localhost:7070/api/adam123/motorhomes
    @GetMapping
    public List<Motorhome> getAll(@PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // GET http://localhost:7070/api/adam123/motorhomes/5
    @GetMapping("/{id}")
    public Motorhome get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // POST http://localhost:7070/api/adam123/motorhomes
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Motorhome motorhome, @PathVariable(name = "key") String key) {
        // create
    }

    //PUT http://localhost:7070/api/adam123/motorhomes/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Motorhome motorhome, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //update
    }

    // PUT http://localhost:7070/api/adam123/motorhomes/12254/status
    @GetMapping("/{id}/status")
    public Status updateStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //return motorhomeService.changeStatus(id);
        return null;
    }

    // DELETE http://localhost:7070/api/adam123/motorhomes/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        // delete
    }
}
