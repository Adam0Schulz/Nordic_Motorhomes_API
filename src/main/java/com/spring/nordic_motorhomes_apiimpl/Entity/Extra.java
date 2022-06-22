package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Wanesa
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "extras")
public class Extra {

    @Id
    @SequenceGenerator(name ="extra_sequence",
            sequenceName = "extra_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "extra_sequence"
    )
    private long ID;
    private String type;
    private double price;

    @JsonIgnore  //just in case of retrieving data using api - prevent recursion error
    @ManyToMany(mappedBy = "extras")
    private Set<Booking> bookings = new HashSet<>();

}
