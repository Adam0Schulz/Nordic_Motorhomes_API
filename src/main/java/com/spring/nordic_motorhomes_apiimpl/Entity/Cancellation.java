package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

// Wanesa
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cancellations") // Naming the database table
public class Cancellation {
    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="cancelledBooking_sequence",
            sequenceName = "cancelledBooking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "cancelledBooking_sequence"
    )
    private long ID;

    @JsonIgnore
    @OneToOne    @JoinColumn(name = "bookingID", referencedColumnName = "ID")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "feeID", referencedColumnName = "ID")
    private CancellationFee fee;


    public Optional<CancellationFee> getFee() {
        return fee == null ? Optional.empty() : Optional.of(fee);
    }
}
