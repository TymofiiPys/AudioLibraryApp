package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@jakarta.persistence.Table(name = "book_authors", schema = "public", catalog = "AudioLib")
@Data
public class BookAuthor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private int id;


    @Basic
    @Column(name = "name")
    private String name;
}
