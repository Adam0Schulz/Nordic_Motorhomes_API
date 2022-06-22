package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;

import javax.persistence.*;

// Adam
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_variables") // Naming the database table
public class SystemVariable {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="systemVariable_sequence",
            sequenceName = "systemVariable_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "systemVariable_sequence"
    )
    private int ID;
    private String name;
    private double value;
}
