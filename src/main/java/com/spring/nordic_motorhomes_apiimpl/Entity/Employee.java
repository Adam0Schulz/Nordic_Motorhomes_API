package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Adam
@Entity
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "employees") // Naming the database table
public class Employee {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="employee_sequence",
        sequenceName = "employee_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
        generator = "employee_sequence"
    )
    private long ID;

    //  Foreign key
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Set<Booking> bookings = new HashSet<>();

    // Other Attributes
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private int CPR;
    private String title;
    private String email;
    private String password;

}
