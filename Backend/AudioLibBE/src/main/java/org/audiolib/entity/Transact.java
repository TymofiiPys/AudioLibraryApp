package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.audiolib.dto.AudCarSalesDTO;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedNativeQuery(name = "Transact.findAllRentedOnDate",
        query = "SELECT * FROM get_rents_by_date(:date)",
        resultSetMapping = "Mapping.AudCarSalesDTO")
@SqlResultSetMapping(name = "Mapping.AudCarSalesDTO",
        classes = @ConstructorResult(targetClass = AudCarSalesDTO.class,
                columns = {@ColumnResult(name = "audio_carrier_id"),
                        @ColumnResult(name = "quantity"),
                        @ColumnResult(name = "date")}))
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
