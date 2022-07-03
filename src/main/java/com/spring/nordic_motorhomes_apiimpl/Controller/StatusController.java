package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Repository.BookingRepository;
import com.spring.nordic_motorhomes_apiimpl.Service.BookingService;
import com.spring.nordic_motorhomes_apiimpl.Service.MotorhomeService;
import com.spring.nordic_motorhomes_apiimpl.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/{key}/statuses")
public class StatusController {

    //Dependencies
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/statuses
    @GetMapping
    public List<Status> getAll(@PathVariable(name = "key") String key) {
        return statusService.getAll();
    }

    // GET http://localhost:7070/api/adam123/statuses/5
    @GetMapping("/{id}")
    public Status get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        return statusService.get(id).orElse(null);
    }

    // POST http://localhost:7070/api/adam123/statuses
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Status status, @PathVariable(name = "key") String key) {
        statusService.save(status);
    }

    //PUT http://localhost:7070/api/adam123/statuses/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Status status, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        statusService.update(id, status);
    }

    // DELETE http://localhost:7070/api/adam123/statuses/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        statusService.delete(id);
    }



}
