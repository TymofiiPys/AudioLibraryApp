package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "popular_songs", schema = "public", catalog = "AudioLib")
@Data
public class SongListens {
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
    @Column(name = "listened")
    private Long listened;
}
