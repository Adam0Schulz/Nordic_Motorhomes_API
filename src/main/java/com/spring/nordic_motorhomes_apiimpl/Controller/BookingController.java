package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Booking;
import com.spring.nordic_motorhomes_apiimpl.Entity.Status;
import com.spring.nordic_motorhomes_apiimpl.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/bookings")
public class BookingController {

    //Dependencies
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Mapping / Routes / Endpoints
    // Crud
    // GET http://localhost:7070/api/adam123/bookings
    @GetMapping
    public List<Booking> getAll(@PathVariable(name = "key") String key) {
        return bookingService.getAll();
    }

    // GET http://localhost:7070/api/adam123/bookings/12554
    @GetMapping( "/{id}")
    public Booking get(@PathVariable(name = "key") String key, @PathVariable(name = "id") Long id) {
        return bookingService.get(id).orElse(null);
    }

    // POST http://localhost:7070/api/adam123/bookings
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestParam(name = "motorhomeid") Long motorhomeID, @RequestParam(name = "customerid") Long customerID, @RequestParam(name = "employeeid") Long employeeID, @RequestParam(name = "statusid") Long statusID, @PathVariable(name = "key") String key, @RequestBody Booking booking) {
        bookingService.save(booking, motorhomeID, customerID, employeeID, statusID);
    }

    // POST http://localhost:7070/api/adam123/bookings
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestParam(name = "motorhomeid") Long motorhomeID, @RequestParam(name = "customerid") Long customerID, @RequestParam(name = "employeeid") Long employeeID, @RequestParam(name = "statusid") Long statusID, @RequestParam(name = "extraids") List<Long> extraIDs, @PathVariable(name = "key") String key, @RequestBody Booking booking) {
        bookingService.save(booking, motorhomeID, customerID, employeeID, extraIDs, statusID);
    }

    // PUT http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable(name = "key") String key, @RequestBody Booking booking, @PathVariable(name = "id") Long id) {
        bookingService.update(id, booking);
    }

    // DELETE http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        bookingService.delete(id);
    }


    // Filtering / Sorting / Pagination

    /*
    // GET http://localhost:7070/api/adam123/bookings?status=active
    @GetMapping("/")
    public List<Booking> getByStatus(@RequestParam(name = "status") String statusString, @PathVariable(name = "key") String key) {
        //return statusService.getAll(statusString);
    }

    // GET http://localhost:7070/apiadam123/bookings?motorhome=5
    @GetMapping("/")
    public List<Booking> getByMotorhome(@RequestParam(name = "motorhome") String motorhomeString, @PathVariable(name = "key") String key) {
        //return motorhomeService.getAll(motorhomeString);
    }
    */

}

