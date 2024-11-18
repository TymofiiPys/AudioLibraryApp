package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "popular_books", schema = "public", catalog = "AudioLib")
@Data
public class BookListens {
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
    @Column(name = "listened")
    private Long listened;
}
