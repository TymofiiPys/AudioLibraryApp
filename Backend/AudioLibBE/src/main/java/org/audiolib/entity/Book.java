package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "books", schema = "public", catalog = "AudioLib")
public class Book {
    @Id
    @jakarta.persistence.Column(name = "id")
    private Long id;
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
