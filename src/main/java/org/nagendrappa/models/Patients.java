package org.nagendrappa.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;


@Entity
@Data
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "doctorPatient",
            joinColumns = @JoinColumn(name = "patientId"),
            inverseJoinColumns = @JoinColumn(name = "doctorId")
    )
    private Set<Doctor> doctors = new HashSet<>();

}
