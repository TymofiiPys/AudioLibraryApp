package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private long id;

    @Basic
    @Column(name = "user_id")
    private String userId;

    @Basic
    @Column(name = "audio_id")
    private long audioId;

    @Basic
    @Column(name = "mark")
    private short mark;

    @Basic
    @Column(name = "text")
    private String text;
}
