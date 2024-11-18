package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Getter
@Table(name = "song_view", schema = "public", catalog = "AudioLib")
public class SongView {
    @Basic
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "artist")
    private String artist;
    @Basic
    @Column(name = "album")
    private String album;
    @Basic
    @Column(name = "duration")
    private Object duration;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "genre")
    private String genre;
}
