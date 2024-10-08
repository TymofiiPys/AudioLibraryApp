package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "genre_books", schema = "public", catalog = "AudioLib")
@Data
public class GenreBooks {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private int id;


    @Basic
    @Column(name = "name")
    private String name;
}
