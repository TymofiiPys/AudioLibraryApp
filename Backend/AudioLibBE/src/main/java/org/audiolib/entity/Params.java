package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Params {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "value")
    private String value;
}
