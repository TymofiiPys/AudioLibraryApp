package org.audiolib.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.Objects;

//@Entity
//@Data
//@NoArgsConstructor
//public class AudCarSalesDTO {
//    @Id
//    private Long id = null;
//    private Long carrier_id;
//    private Integer quantity;
//    private Date date;
//    public AudCarSalesDTO(Long carrier_id, Integer quantity, Date date) {
//        this.carrier_id = carrier_id;
//        this.quantity = quantity;
//        this.date = date;
//    }
//}
public record AudCarSalesDTO(Long carrier_id, Integer quantity, Date _date) {
}