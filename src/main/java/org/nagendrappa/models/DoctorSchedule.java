package org.nagendrappa.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
