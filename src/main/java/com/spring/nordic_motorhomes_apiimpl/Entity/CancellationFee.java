package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

// Wanesa
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cancellation_fees") // Naming the database table
public class CancellationFee {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="cancellationFees_sequence",
            sequenceName = "cancellationFees_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "cancellationFees_sequence"
    )
    private int ID;

    @OneToMany(mappedBy = "fee")
    private Set<CancelledBooking> cancelledBookings;

    // Other Attributes
    private String name;

    //in case of cancellation on the rental day the max will be 1 and min -1
    private int maxDaysBefore;
    private int minDaysBefore;
    private int percentage;
    private int minimum;
}
