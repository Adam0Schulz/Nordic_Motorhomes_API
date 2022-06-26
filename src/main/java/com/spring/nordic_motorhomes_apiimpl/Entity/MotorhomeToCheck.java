package com.spring.nordic_motorhomes_apiimpl.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "motorhomes_to_check") // Naming the database table
public class MotorhomeToCheck {

    // Attributes/Columns
    //  Primary key
    @Id
    @SequenceGenerator(name ="motorhomesToCheck_sequence",
            sequenceName = "motorhomesToCheck_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "motorhomesToCheck_sequence"
    )
    private int ID;

    //  Foreign key
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "motorhomeID", referencedColumnName = "ID")
    private Motorhome motorhome;
}
