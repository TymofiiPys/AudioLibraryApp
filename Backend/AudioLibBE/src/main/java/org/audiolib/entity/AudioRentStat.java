package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "audio_rent_stats_verb", schema = "public", catalog = "AudioLib")
@Data
public class AudioRentStat {
    @Basic
    @Id
    @Column(name = "id")
    private Long audioId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "creator")
    private String creator;
    @Basic
    @Column(name = "avgrent")
    private Double avgrent;
    @Basic
    @Column(name = "rents")
    private Long rents;
}
