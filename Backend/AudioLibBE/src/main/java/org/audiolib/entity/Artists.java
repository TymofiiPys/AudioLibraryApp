package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artists {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;
}
