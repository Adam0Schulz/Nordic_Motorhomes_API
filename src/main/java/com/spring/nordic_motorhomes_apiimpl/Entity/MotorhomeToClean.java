package com.spring.nordic_motorhomes_apiimpl.Entity;

import lombok.*;

import javax.persistence.*;

// Wanesa Emie
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "motorhomes_to_clean") // Naming the database table
public class MotorhomeToClean {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="motorhomeToClean_sequence",
            sequenceName = "motorhomeToClean_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "motorhomeToClean_sequence"
    )
    private int ID;

    //  Foreign key
    @OneToOne
    @JoinColumn(name = "motorhomeID", referencedColumnName = "ID")
    private Motorhome motorhome;
}
