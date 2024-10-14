package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "books", schema = "public", catalog = "AudioLib")
public class Book {
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