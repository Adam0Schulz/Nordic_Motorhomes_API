package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.*;
import com.spring.nordic_motorhomes_apiimpl.Repository.BookingRepository;
import com.spring.nordic_motorhomes_apiimpl.Repository.CancelledBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Adam Simona
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final MotorhomeService motorhomeService;
    private final EmployeeService employeeService;
    private final ExtraService extraService;
    private final CustomerService customerService;
    private final SeasonService seasonService;
    private final SystemVariableService systemVariableService;
    private final CancelledBookingRepository cancelledBookingRepository;
    private final CancellationFeeService cancellationFeeService;

    @Autowired
    public BookingService (BookingRepository bookingRepository,
                           MotorhomeService motorhomeService,
                           EmployeeService employeeService,
                           ExtraService extraService,
                           CustomerService customerService,
                           SeasonService seasonService,
                           SystemVariableService systemVariableService,
                           CancelledBookingRepository cancelledBookingRepository,
                           CancellationFeeService cancellationFeeService) {
        this.bookingRepository = bookingRepository;
        this.motorhomeService = motorhomeService;
        this.employeeService = employeeService;
        this.extraService = extraService;
        this.customerService = customerService;
        this.seasonService = seasonService;
        this.systemVariableService = systemVariableService;
        this.cancelledBookingRepository = cancelledBookingRepository;
        this.cancellationFeeService = cancellationFeeService;
    }

    // Create booking - creates and saves booking
    public Booking createBooking( Set<Extra> extras, Customer customer, Motorhome motorhome, Employee employee, Date start, Date end, String pickUp, Time pickUpTime, String dropOff, double total) {

        // Creating new booking
        Booking newBooking = Booking.builder()
                .extras(extras)
                .customer(customer)
                .motorhome(motorhome)
                .employee(employee)
                .startDate(start)
                .endDate(end)
                .pickUpLocation(pickUp)
                .pickUpTime(pickUpTime)
                .dropOffLocation(dropOff)
                .totalPrice(total)
                .build();

        // Creating future booking
        FutureBooking futureBooking = FutureBooking.builder()
                .booking(newBooking)
                .build();

        // saving data to database
        newBooking.setFutureBooking(futureBooking);
        bookingRepository.save(newBooking);
        return newBooking;
    }

    // Save booking - saves booking (overload)
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }
    // Cancel booking
    public Booking cancelBooking(long bookingID) {

        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        CancellationFee fee = cancellationFeeService.selectFee(booking);

        // Error handling
        if(booking == null || booking.getPastBooking() != null || fee == null) {
            return null;
        }

        // Creation of the cancelled booking
        CancelledBooking cancelledBooking = CancelledBooking.builder()
                .booking(booking)
                .fee(fee)
                .build();

        // Updating and saving of the booking
        booking.setCancelledBooking(cancelledBooking);
        booking.setFutureBooking(null);
        booking.setActiveBooking(null);
        bookingRepository.save(booking);

        return booking;
    }

    // Update booking
    public Booking updateBooking(Long ID, Booking booking) {
        booking.setID(ID);
        return bookingRepository.existsById(ID) ? bookingRepository.save(booking) : null;
    }

    // Delete booking
    public void deleteBooking(Long ID) {
        bookingRepository.deleteById(ID);
    }

    // Potentionally redundant
    // Add booking - creates customer if they don't exist and creates booking (return true/false depending on if everything's alright)
    /*public Booking addBooking(int customerCpr, String customerFirst, String customerLast, int customerPhone, Date start, Date end, long motorhomeID, Set<Integer> extraIDs, String pickUp, String dropOff, Time pickUpTime, long empID) {

        // Set up
        Motorhome motorhome = motorhomeService.getMotorhomeById(motorhomeID);
        Employee employee = employeeService.getEmpById(empID);
        Set<Extra> extras = extraService.getExtrasByIds(extraIDs);


        // Error handling
        if (motorhome == null || employee == null || extras == null || !(motorhomeService.isAvailableDuring(motorhome, start.toLocalDate(), end.toLocalDate()))) { return null; }

        // Calculating the total price of the booking
        double total = getTotalPrice(motorhome, start.toLocalDate(), end.toLocalDate(), extras);

        // Creating Customer and Booking
        Customer c = customerService.getOrCreateCustomer(customerCpr,customerFirst,customerLast,customerPhone);
        Booking booking = createBooking(extras, c, motorhome, employee, start, end, pickUp, pickUpTime, dropOff, total);

        return booking;
    }*/


    // Get all the bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get all the bookings - sorted
    public List<Booking> getAllBookings(Sort sort) {
        return bookingRepository.findAll(sort);
    }

    // Get booking by name
    public List<Booking> getBookingByName(String name) {

        List<Booking> bookings = new ArrayList<>();
        List<Booking> allBookings = getAllBookings();

        for(Booking booking : allBookings)  {
            Customer customer = booking.getCustomer();
            if(customer.getFirstName().equals(name) || customer.getLastName().equals(name)) {
                bookings.add(booking);
            }
        }

        return bookings;
    }

    // Get booking by ID
    public Booking getBookingById(long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    // Search bookings based on any attribute
    public List<Booking> searchBookings(String searchString) {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allBookings = getAllBookings();

        for(Booking booking : allBookings) {
            String bookingString = booking.toString();
            if(bookingString.contains(searchString)) {
                bookings.add(booking);
            }
        }

        return bookings;
    }


    // Get active bookings
    public List<Booking> getActiveBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allBookings = getAllBookings();
        for(Booking booking : allBookings) {
            if(booking.getActiveBooking() != null) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Get sorted active bookings - it's easier to use sql for sorting
    public List<Booking> getSortedActiveBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allSortedBookings = getAllBookings(Sort.by(Sort.Direction.ASC, "endDate"));
        List<Booking> activeBookings = getActiveBookings();

        for(Booking booking : allSortedBookings) {
            if(activeBookings.contains(booking)) {
                bookings.add(booking);
            }
        }

        return bookings;

    }

    // Get today's active bookings - active bookings that have end today (hence will be dropped off)
    public List<Booking> getTodaysActiveBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> activeBookings = getActiveBookings();
        LocalDate currentDate = LocalDate.now();

        for (Booking booking : activeBookings) {
            LocalDate bookingEndDate = booking.getEndDate().toLocalDate();
            if(bookingEndDate.equals(currentDate)) {
                bookings.add(booking);
            }
        }

        return bookings;
    }

    // Get future bookings
    public List<Booking> getFutureBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allBookings = getAllBookings();
        for(Booking booking : allBookings) {
            if(booking.getFutureBooking() != null) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Get sorted future bookings
    public List<Booking> getSortedFutureBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allSortedBookings = getAllBookings(Sort.by(Sort.Direction.ASC, "startDate"));
        List<Booking> futureBookings = getFutureBookings();

        for(Booking booking : allSortedBookings) {
            if(futureBookings.contains(booking)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Get today's active bookings - active bookings that have end today (hence will be dropped off)
    public List<Booking> getTodaysFutureBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> futureBookings = getFutureBookings();
        LocalDate currentDate = LocalDate.now();

        for (Booking booking : futureBookings) {
            LocalDate bookingStartDate = booking.getStartDate().toLocalDate();
            if(bookingStartDate.equals(currentDate)) {
                bookings.add(booking);
            }
        }

        return bookings;
    }

    // Get past bookings
    public List<Booking> getPastBookings() {
        List<Booking> bookings = new ArrayList<>();
        List<Booking> allBookings = getAllBookings();
        for(Booking booking : allBookings) {
            if(booking.getPastBooking() != null) {
                bookings.add(booking);
            }
        }
        return bookings;
    }


    // Checks if booking is containing a date
    public boolean isBookingContainingDate(Booking booking, LocalDate date) {
        List<Booking> bookingsContainingDate = getBookingByDate(date);
        if(bookingsContainingDate.contains(booking)) {
            return true;
        }
        return false;
    }

    // Checks if booking is containing a date (including a buffer)
    public boolean isBookingContainingDate(Booking booking, LocalDate date, int bufferDays) {
        List<Booking> bookingsContainingDate = getBookingByDate(date, bufferDays);
        if(bookingsContainingDate.contains(booking)) {
            return true;
        }
        return false;
    }


    // Get Booking by date - returns a list of bookings that contain the given date
    public List<Booking> getBookingByDate(LocalDate date) {

        List<Booking> bookings = new ArrayList<Booking>();
        List<Booking> allBookings = getAllBookings();

        for (Booking booking : allBookings) {
            LocalDate startDate = booking.getStartDate().toLocalDate();
            LocalDate endDate = booking.getEndDate().toLocalDate();

            // Condition check if the date is between start and end date of the booking
            if ((startDate.isBefore(date) || startDate.isEqual(date)) && (endDate.isAfter(date) || endDate.isEqual(date))) {
                bookings.add(booking);
            }

        }


        return bookings;
    }

    // Get Booking by date - returns a list of bookings that contain the given date (adds a buffer on the end date of the booking)
    public List<Booking> getBookingByDate(LocalDate date, int bufferDays) {

        List<Booking> bookings = new ArrayList<Booking>();
        List<Booking> allBookings = getAllBookings();

        for (Booking booking : allBookings) {
            LocalDate startDate = booking.getStartDate().toLocalDate();
            LocalDate endDate = booking.getEndDate().toLocalDate().plus(bufferDays, ChronoUnit.DAYS);

            // Condition check if the date is between start and end date of the booking
            if ((startDate.isBefore(date) || startDate.isEqual(date)) && (endDate.isAfter(date) || endDate.isEqual(date))) {
                bookings.add(booking);
            }

        }


        return bookings;
    }

    // Get Booking by start date
    public List<Booking> getBookingByStartDate(Date date) {
        List<Booking> bookings = bookingRepository.findByStartDate(date);
        return bookings;
    }

    // Booking by end date
    public List<Booking> getBookingByEndDate(Date date) {
        List<Booking> bookings = bookingRepository.findByEndDate(date);
        return bookings;
    }

    // Get previous bookings - returns bookings that ended before specified date
    public List<Booking> getPreviousBookings(LocalDate date) {
        List<Booking> previousBookings = new ArrayList<Booking>();
        List<Booking> bookings = getAllBookings();
        for(Booking booking : bookings) {
            if(booking.getEndDate().toLocalDate().isBefore(date)) {
                previousBookings.add(booking);
            }
        }

        return previousBookings;
    }


    // Get booking by motorhome id
    public List<Booking> getBookingByMotorhomeID(long motorhomeID) {
        return bookingRepository.findByMotorhomeID(motorhomeID);
    }


    // Get total price - calculates and returns total price of the booking
    public double getTotalPrice(Motorhome motorhome, LocalDate start, LocalDate end, Set<Extra> extras) {
        int days = (int) ChronoUnit.DAYS.between(start, end);
        double seasonPercentage = seasonService.getSeason(start).getPercentage() + 1;
        return ((motorhome.getBasePrice() * days) + extraService.getExtrasTotalPrice(extras)) * seasonPercentage;
    }

    // Picked up - adds given booking to active bookings
    public Booking pickedUp(long bookingID) {
        return addToActive(bookingID);

    }

    // Dropped off - moves motorhome attached to given booking to motorhomes to be checked
    public Booking droppedOff(long bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking == null) {
            return null;
        }
        motorhomeService.addToCheck(booking.getMotorhome().getID());
        return booking;
    }

    // Dropped off - moves motorhome attached to given booking to motorhomes to be checked (adds additional kilometers fee to the total of the booking)
    public Booking droppedOff(long bookingID, int additionalKilometers) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking == null) {
            return null;
        }
        motorhomeService.addToCheck(booking.getMotorhome().getID());

        // calculate the additional fee for drop off
        double total = booking.getTotalPrice();
        total += additionalKilometers * systemVariableService.getAdditionalKilometerFee();

        booking.setTotalPrice(total);
        return booking;
    }

    // Add to active - moves booking with given id to active bookings
    public Booking addToActive(long bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking == null) {
            return null;
        }
        ActiveBooking activeBooking = ActiveBooking.builder()
                .booking(booking)
                .build();
        booking.setPastBooking(null);
        booking.setFutureBooking(null);
        booking.setActiveBooking(activeBooking);
        bookingRepository.save(booking);

        return booking;
    }

    // Add to past - moves booking with given id to past bookings
    public Booking addToPast(long bookingID) {
        Booking booking = bookingRepository.findById(bookingID).orElse(null);
        if(booking == null) {
            return null;
        }
        PastBooking pastBooking = PastBooking.builder()
                .booking(booking)
                .build();
        booking.setPastBooking(pastBooking);
        booking.setFutureBooking(null);
        booking.setActiveBooking(null);
        bookingRepository.save(booking);

        return booking;
    }

    // not finished
    public String getStatus(long bookingID) {

        return null;
    }
}
