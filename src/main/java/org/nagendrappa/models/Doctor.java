package org.nagendrappa.models;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "hopitalId")
    private Hospital hospital;


    @ManyToOne
    @JoinColumn(name = "specialtyId")
    private Speciality specialty;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private DoctorSchedule schedule;



}
