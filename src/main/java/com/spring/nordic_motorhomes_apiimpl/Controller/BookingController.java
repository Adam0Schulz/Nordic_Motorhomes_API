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
    // GET http://localhost:7070/api/adam123/bookings
    @GetMapping
    public List<Booking> getAll(@PathVariable(name = "key") String key) {
        return bookingService.getAllBookings();
    }

    // GET http://localhost:7070/api/adam123/bookings/12554
    @GetMapping( "/{id}")
    public Booking get(@PathVariable(name = "key") String key, @PathVariable(name = "id") Long id) {
        return bookingService.getBookingById(id);
    }

    // GET http://localhost:7070/api/adam123/bookings/12254/status
    @GetMapping("/{id}/status")
    public Status getStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        return bookingService.getStatus(id);
    }

    // GET http://localhost:7070/api/adam123/bookings?status=active
    @GetMapping("/?status={status}")
    public List<Booking> getAll(@PathVariable(name = "status") String status,@PathVariable(name = "key") String key) {
        // get all bookings with given status
        return null;
    }

    // POST http://localhost:7070/api/adam123/bookings
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@PathVariable(name = "key") String key, @RequestBody Booking booking) {
        bookingService.saveBooking(booking);
    }

    // PUT http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable(name = "key") String key, @RequestBody Booking booking, @PathVariable(name = "id") Long id) {
        bookingService.updateBooking(id, booking);
    }

    // PUT http://localhost:7070/api/adam123/bookings/12254/status
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable(name = "id") Long id, @PathVariable(name = "key") String key) {
        //bookingService.changeStatus(id);
    }

    // DELETE http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        bookingService.deleteBooking(id);
    }



}

