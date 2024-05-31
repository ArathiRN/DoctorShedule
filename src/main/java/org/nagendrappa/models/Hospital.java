package org.nagendrappa.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
