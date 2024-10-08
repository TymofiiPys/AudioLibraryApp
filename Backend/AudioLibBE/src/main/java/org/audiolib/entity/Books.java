package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Books {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private long id;


    @Basic
    @Column(name = "author")
    private int author;


    @Basic
    @Column(name = "publisher")
    private String publisher;


    @Basic
    @Column(name = "genre")
    private Integer genre;
}
