package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Audio {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private long id;


    @Basic
    @Column(name = "name")
    private String name;


    @Basic
    @Column(name = "year")
    private int year;


    @Column(name = "duration")
    private String duration;


    @Basic
    @Column(name = "date_added")
    private Date dateAdded;
}
