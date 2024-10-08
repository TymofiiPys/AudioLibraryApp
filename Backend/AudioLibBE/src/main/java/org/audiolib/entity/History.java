package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@IdClass(org.audiolib.entity.HistoryPK.class)
@Data
public class History {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "user_id")
    private String userId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "audio_id")
    private long audioId;

    @Basic
    @Column(name = "amt_listened")
    private int amtListened;
}
