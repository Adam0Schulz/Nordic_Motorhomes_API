package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordic_motorhomes_apiimpl.Entity.*;
import com.spring.nordic_motorhomes_apiimpl.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    // Dependencies
    private final BookingRepository bookingRepo;
    private final StatusService statusSer;
    private final MotorhomeService motorhomeSer;
    private final CustomerService customerSer;
    private final EmployeeService employeeSer;
    private final ExtraService extraSer;
    private final SeasonService seasonSer;
    private final SystemVariableService variableSer;

    @Autowired
    public BookingService(BookingRepository bookingRepo,
                          StatusService statusSer,
                          MotorhomeService motorhomeService,
                          CustomerService customerSer,
                          EmployeeService employeeSer,
                          ExtraService extraSer,
                          SeasonService seasonSer,
                          SystemVariableService variableSer) {
        this.bookingRepo = bookingRepo;
        this.statusSer = statusSer;
        this.motorhomeSer = motorhomeService;
        this.customerSer = customerSer;
        this.employeeSer = employeeSer;
        this.extraSer = extraSer;
        this.seasonSer = seasonSer;
        this.variableSer = variableSer;
    }

    // have multiple overloads
    // Save booking
    public Optional<Booking> save(Booking booking, Long motorhomeID, Long customerID, Long employeeID, List<Long> extraIDs, Long statusID) {

        Optional<Motorhome> motorhome = motorhomeSer.get(motorhomeID);
        Optional<Customer> customer = customerSer.get(customerID);
        Optional<Employee> employee = employeeSer.get(employeeID);
        List<Extra> extras = extraIDs.stream().map(extraSer::get)
                                            .filter(Optional::isPresent)
                                            .map(Optional::get)
                                            .collect(Collectors.toList());
        Optional<Status> status = statusSer.get(statusID);

        if(motorhome.isEmpty()
          || customer.isEmpty()
          || employee.isEmpty()
          || status.isEmpty())
        return Optional.empty();

        booking.setMotorhome(motorhome.get());
        booking.setCustomer(customer.get());
        booking.setEmployee(employee.get());
        booking.setExtras(extras);
        booking.setStatus(status.get());
        booking.setTotalPrice(calcTotal(booking));

        return !(motorhomeSer.isAvailable(motorhomeID, booking.getStartDate().toLocalDate(), booking.getEndDate().toLocalDate()))
                ? Optional.empty()
                : Optional.of(bookingRepo.save(booking));
    }

    // Get booking
    public Optional<Booking> get(Long id) {
        return bookingRepo.findById(id);
    }

    // Get bookings
    public List<Booking> getAll() {
        return bookingRepo.findAll();
    }

    // Get bookings - sorted
    public List<Booking> getAll(Sort sort) {
        return bookingRepo.findAll(sort);
    }

    // Get bookings by status
    public List<Booking> getByStatus(String statusKeyword) {
        return bookingRepo
                .findAll()
                .stream().filter(booking -> booking
                        .getStatus()
                            .getKeyword()
                                .equalsIgnoreCase(statusKeyword))
                .collect(Collectors.toList());
    }

    // Get bookings by status - sorted
    public List<Booking> getByStatus(String statusKeyword, Sort sort) {
        return bookingRepo
                .findAll(sort)
                .stream().filter(booking -> booking
                        .getStatus()
                        .getKeyword()
                        .equalsIgnoreCase(statusKeyword))
                .collect(Collectors.toList());
    }

    // Update booking
    public Optional<Booking> update(Long id, Booking booking) {
        booking.setID(id);
        return !(bookingRepo.existsById(id)) ? Optional.empty() : Optional.of(bookingRepo.save(booking));
    }

    // Delete booking
    public void delete(Long id) {
        bookingRepo.deleteById(id);
    }

    // Update/Change status of booking
    public Optional<Booking> updateStatus(Long bookingID, Long statusID) {
        Optional<Status> status = statusSer.get(statusID);
        Optional<Booking> booking = get(bookingID);

        if(status.isEmpty() || booking.isEmpty()) return Optional.empty();

        booking.get().setStatus(status.get());

        return booking;
    }

    // Cancel a booking
    public Optional<Booking> cancel(Long bookingID, Long cancelStatusID) {

        Optional<Booking> booking = updateStatus(bookingID, cancelStatusID);
        if (booking.isEmpty()) return Optional.empty();

        Cancellation cancell = Cancellation.builder()
                .booking(booking.get())
                .build();
        // Not sure if these two steps are required
        booking.get().setCancellation(cancell);
        return Optional.of(bookingRepo.save(booking.get()));
    }

    // Total price
    private double calcTotal(Booking booking) {
        LocalDate start = booking.getStartDate().toLocalDate();
        LocalDate end = booking.getEndDate().toLocalDate();

        int days = (int) ChronoUnit.DAYS.between(start, end);
        // percentage values are represented by values 0 - 100% -> 0.0 - 1.0
        double seasonPer = seasonSer.get(start).map(Season::getPercentage).orElse(-1.0) + 1;

        return (
                    (booking.getMotorhome().getBasePrice() * days)
                    + extraSer.getTotal(booking.getExtras())
                ) * seasonPer;
    }

    // End booking (drop off)
    public Optional<Booking> end(Long bookingID, Long bookingStatusID, Long motorhomeStatusID) {
        long motorhome = get(bookingID)
                .map(Booking::getMotorhome)
                .map(Motorhome::getID)
                .orElse(-1L);
        long bookStatus = statusSer.get(bookingStatusID)
                .map(Status::getID)
                .orElse(-1L);
        long motorStatus = statusSer.get(motorhomeStatusID)
                .map(Status::getID)
                .orElse(-1L);

        Optional<Motorhome> motor = motorhomeSer.updateStatus(motorhome, motorStatus);
        Optional<Booking> book = updateStatus(bookingID, bookStatus);

        return motor.isEmpty() ? Optional.empty() : book;

    }

    // End booking (drop off) - with additional kilometers
    public Optional<Booking> end(Long bookingID, Long bookingStatusID, Long motorhomeStatusID, int additKilo) {
        Optional<Booking> booking = end(bookingID, bookingStatusID, motorhomeStatusID);

        double fee = additKilo * variableSer.get("additional kilometer fee");
        booking.ifPresent(b -> b.setTotalPrice(b.getTotalPrice() + fee));

        return booking;

    }




}
