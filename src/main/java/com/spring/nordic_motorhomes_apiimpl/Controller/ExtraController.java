package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Extra;
import com.spring.nordic_motorhomes_apiimpl.Service.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/extras")
public class ExtraController {

    // Dependencies
    private final ExtraService extraService;

    @Autowired
    public ExtraController(ExtraService extraService) {
        this.extraService = extraService;
    }


    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/extras
    @GetMapping
    public List<Extra> getAll(@PathVariable(name = "key") String key) {
        return extraService.getAll();
    }

    // GET http://localhost:7070/api/adam123/extras/5
    @GetMapping("/{id}")
    public Extra get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        return extraService.get(id).orElse(null);
    }

    // POST http://localhost:7070/api/adam123/extras
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Extra extra, @PathVariable(name = "key") String key) {
        extraService.save(extra);
    }

    //PUT http://localhost:7070/api/adam123/extras/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Extra extra, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        extraService.update(id, extra);
    }

    // DELETE http://localhost:7070/api/adam123/extra/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key, @PathVariable(name = "id") Long id) {
        extraService.delete(id);
    }
}