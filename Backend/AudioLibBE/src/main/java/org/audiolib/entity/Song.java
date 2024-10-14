package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "songs", schema = "public", catalog = "AudioLib")
public class Song {
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
