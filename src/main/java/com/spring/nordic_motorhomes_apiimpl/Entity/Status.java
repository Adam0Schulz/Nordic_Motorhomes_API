package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Statuses")
public class Status extends GeneralEntity{
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

    //  Foreign key
    @OneToMany(mappedBy = "status")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "status")
    private List<Motorhome> motorhomes;

    //  Other attributes
    private String keyword;
    private String description;

    public String toString() {
        return this.getID() + "-" + this.getKeyword();
    }

}
