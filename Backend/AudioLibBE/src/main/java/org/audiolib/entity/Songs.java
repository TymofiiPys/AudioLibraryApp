package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Songs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "artist")
    private int artist;
    @Basic
    @Column(name = "album")
    private String album;
    @Basic
    @Column(name = "genre")
    private Integer genre;
}
