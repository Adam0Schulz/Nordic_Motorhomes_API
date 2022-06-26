package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Extra> extras = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "ID")
    private Customer customer;


    @ManyToOne()
    @JoinColumn(name = "motorhomeID", referencedColumnName = "ID")
    private Motorhome motorhome;


    @ManyToOne()
    @JoinColumn(name = "employeeID", referencedColumnName = "ID")
    private Employee employee;


    @OneToOne(mappedBy = "booking")
    private ActiveBooking activeBooking;


    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private FutureBooking futureBooking;


    @OneToOne(mappedBy = "booking")
    private PastBooking pastBooking;

    @OneToOne(mappedBy = "booking")
    private CancelledBooking cancelledBooking;


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

    public String toString() {
        return getID() + getCustomer().toString() + getMotorhome().toString() + getTotalPrice();
    }
}