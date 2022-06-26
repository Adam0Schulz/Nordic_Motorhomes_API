package com.spring.nordic_motorhomes_apiimpl.Controller;

import com.spring.nordic_motorhomes_apiimpl.Entity.Booking;
import com.spring.nordic_motorhomes_apiimpl.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{key}/bookings")
public class BookingController {

    //Dependency injection
    BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Mapping
    // GET http://localhost:7070/api/adam123/bookings
    @GetMapping
    public List<Booking> getAllBookings(@PathVariable(name = "key") String authKey) {
        if(authKey.equals("adam1")) {
            return bookingService.getAllBookings();
        }
        return null;
    }

    // GET http://localhost:7070/api/adam123/bookings/12554
    @GetMapping( "/{id}")
    public Booking getBookingById(@PathVariable(name = "key") String authKey, @PathVariable(name = "id") long id) {
        return bookingService.getBookingById(id);
    }

    // POST http://localhost:7070/api/adam123/bookings
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBooking(@PathVariable(name = "key") String key, @RequestBody Booking booking) {
        bookingService.saveBooking(booking);
    }

    // PUT http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBooking(@RequestBody Booking booking, @PathVariable(name = "id") String id) {
        bookingService.updateBooking(id, booking);
    }

    // DELETE http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable(name = "id") String id) {
        bookingService.deleteBooking(id);
    }



}

