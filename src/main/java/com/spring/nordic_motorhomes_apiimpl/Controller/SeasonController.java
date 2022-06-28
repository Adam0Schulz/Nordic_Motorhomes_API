package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Season;
import com.spring.nordic_motorhomes_apiimpl.Entity.SystemVariable;
import com.spring.nordic_motorhomes_apiimpl.Service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/seasons")
public class SeasonController {

    // Dependencies
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/seasons
    @GetMapping
    public List<Season> getAll(@PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // GET http://localhost:7070/api/adam123/seasons/5
    @GetMapping("/{id}")
    public Season get(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        // return
        return null;
    }

    // POST http://localhost:7070/api/adam123/seasons
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void create(@RequestBody Season season, @PathVariable(name = "key") String key) {
        // create
    }

    //PUT http://localhost:7070/api/adam123/seasons/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Season season, @PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //update
    }

    // DELETE http://localhost:7070/api/adam123/seasons/5
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        // delete
    }
}
