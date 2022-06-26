package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

// Wanesa
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "active_bookings") // Naming the database table
public class ActiveBooking {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="activeBooking_sequence",
            sequenceName = "activeBooking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "activeBooking_sequence"
    )
    private long ID;

    //  Foreign key
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookingID", referencedColumnName = "ID")
    private Booking booking;

}
