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
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Mapping / Routes / Endpoints
    // GET http://localhost:7070/api/adam123/bookings
    @GetMapping
    public List<Booking> getAllBookings(@PathVariable(name = "key") String key) {
        return bookingService.getAllBookings();
    }

    // GET http://localhost:7070/api/adam123/bookings/12554
    @GetMapping( "/{id}")
    public Booking getBookingById(@PathVariable(name = "key") String key, @PathVariable(name = "id") Long id) {
        return bookingService.getBookingById(id);
    }

    // GET http://localhost:7070/api/adam123/bookings/12554/status
    @GetMapping( "/{id}/status")
    public Booking getBookingStatus(@PathVariable(name = "key") String key, @PathVariable(name = "id") Long id) {
        //return bookingService.getBookingById(id).getStatus;
        return null;
    }

    // GET http://localhost:7070/api/adam123/bookings/active
    @GetMapping("/active")
    public List<Booking> getActiveBooking(@PathVariable(name = "key") String key) {
        return bookingService.getActiveBookings();
    }

    // GET http://localhost:7070/api/adam123/bookings/past
    @GetMapping("/past")
    public List<Booking> getPastBooking(@PathVariable(name = "key") String key) {
        return bookingService.getPastBookings();
    }

    // GET http://localhost:7070/api/adam123/bookings/upcoming
    @GetMapping("/upcoming")
    public List<Booking> getUpcomingBooking(@PathVariable(name = "key") String key) {
        return bookingService.getFutureBookings();
    }

    // POST http://localhost:7070/api/adam123/bookings
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBooking(@PathVariable(name = "key") String key, @RequestBody Booking booking) {
        bookingService.saveBooking(booking);
    }

    // POST http://localhost:7070/api/adam123/bookings/12554/status
    @PostMapping("/{id}/status")
    public List<Booking> updateBookingStatus(@PathVariable(name = "key") String key) {
        //change status;
        return null;
    }

    // PUT http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBooking(@PathVariable(name = "key") String key, @RequestBody Booking booking, @PathVariable(name = "id") Long id) {
        bookingService.updateBooking(id, booking);
    }

    // DELETE http://localhost:7070/api/adam123/bookings/12554
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable(name = "key") String key,@PathVariable(name = "id") Long id) {
        bookingService.deleteBooking(id);
    }



}

