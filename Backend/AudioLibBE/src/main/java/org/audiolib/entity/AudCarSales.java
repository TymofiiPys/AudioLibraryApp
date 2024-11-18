package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "aud_car_sales", schema = "public", catalog = "AudioLib")
@Data
public class AudCarSales {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "carrier_id")
    private Long carrierId;
    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "date")
    private Date date;
}
