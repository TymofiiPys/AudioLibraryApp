package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "genre_stats", schema = "public", catalog = "AudioLib")
@Data
public class GenreStats {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "is_book")
    private Boolean isBook;
    @Basic
    @Column(name = "active_rents")
    private Long activeRents;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "genre_id")
    private Integer genreId;
}
