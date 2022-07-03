package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// Adam
@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "bookings") // Naming the database table
public class Booking {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private long ID;

    //  Foreign keys
    @ManyToMany
    @JoinTable(
            name="booking_extras",
            joinColumns = @JoinColumn(name = "booking_ID"),
            inverseJoinColumns = @JoinColumn(name = "extra_ID")
    )
    private List<Extra> extras = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "ID")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "motorhomeID", referencedColumnName = "ID")
    private Motorhome motorhome;

    @ManyToOne
    @JoinColumn(name = "statusID", referencedColumnName = "ID")
    private Status status;

    @ManyToOne()
    @JoinColumn(name = "employeeID", referencedColumnName = "ID")
    private Employee employee;




    @OneToOne(mappedBy = "booking")
    private Cancellation cancellation;


    //  Other Attributes
    private Date startDate;
    private Time pickUpTime;
    private Date endDate;
    private String pickUpLocation;
    private String dropOffLocation;
    private double totalPrice;
    private double fuelLevel;

    public void addExtra(Extra extra) {
        extras.add(extra);
    }

    public boolean isContainingDate(LocalDate date) {
        return (startDate.toLocalDate().isBefore(date) || startDate.toLocalDate().isEqual(date))
                && (endDate.toLocalDate().isAfter(date) || endDate.toLocalDate().isEqual(date));
    }

    public boolean isContainingDate(LocalDate date, int buffer) {
        LocalDate startDate = getStartDate().toLocalDate();
        LocalDate endDate = getEndDate().toLocalDate().plus(buffer, ChronoUnit.DAYS);
        return (startDate.isBefore(date) || startDate.isEqual(date))
                && (endDate.isAfter(date) || endDate.isEqual(date));
    }

    public boolean isContainingDate(LocalDate date, double doubleBuffer) {
        int buffer = (int) Math.round(doubleBuffer);
        return isContainingDate(date, buffer);
    }

}