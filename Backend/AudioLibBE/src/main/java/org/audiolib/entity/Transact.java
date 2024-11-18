package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transact {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "audio_carrier_id")
    private Long audioCarrierId;
    @Basic
    @Column(name = "date_rented")
    private Date dateRented;
    @Basic
    @Column(name = "date_end_of_rent")
    private Date dateEndOfRent;
}
