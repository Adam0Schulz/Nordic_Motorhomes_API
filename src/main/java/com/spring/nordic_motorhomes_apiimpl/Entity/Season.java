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
@Table(name = "seasons") // Naming the database table
public class Season extends GeneralEntity{
    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="season_sequence",
            sequenceName = "season_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "season_sequence"
    )
    private long ID;

    //  Other Attributes
    private String name;
    private int startMonth;
    private int endMonth;
    private double percentage;
}
