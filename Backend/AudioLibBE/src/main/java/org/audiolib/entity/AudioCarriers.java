package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "audio_carriers", schema = "public", catalog = "AudioLib")
@Data
public class AudioCarriers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id")
    private long id;


    @Basic
    @Column(name = "audio_id")
    private long audioId;


    @Column(name = "carrier")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Carriers carrier;


    @Basic
    @Column(name = "amt_available")
    private Integer amtAvailable;

    public enum Carriers {digital, cd, vinyl}
}
