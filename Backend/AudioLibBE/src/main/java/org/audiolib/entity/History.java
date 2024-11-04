package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@IdClass(org.audiolib.entity.HistoryPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "user_id")
    private String userId;

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "audio_id")
    private long audioId;

    @Basic
    @Column(name = "amt_listened")
    private int amtListened;
}
