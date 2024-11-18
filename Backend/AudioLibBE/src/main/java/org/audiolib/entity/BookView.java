package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.Objects;

@Entity
@Immutable
@Getter
@Table(name = "book_view", schema = "public", catalog = "AudioLib")
public class BookView {
    @Basic
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "duration")
    private Object duration;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "publisher")
    private String publisher;
}
