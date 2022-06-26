package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

// Wanesa
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "past_bookings") // Naming the database table
public class PastBooking {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="pastBooking_sequence",
            sequenceName = "pastBooking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "pastBooking_sequence"
    )
    private int ID;

    // Foreign key
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bookingID", referencedColumnName = "ID")
    private Booking booking;
}
