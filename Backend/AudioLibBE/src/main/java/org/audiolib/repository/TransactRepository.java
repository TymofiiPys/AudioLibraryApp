package org.audiolib.repository;

import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.entity.AudCarSales;
import org.audiolib.entity.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TransactRepository extends JpaRepository<Transact, Long> {
    @Query(value = "SELECT new AudCarSalesDTO(t.audioCarrierId, COUNT(*), ?1)" +
            " from Transact t " +
            " where ?1 between t.dateRented and t.dateEndOfRent" +
            " group by t.audioCarrierId")
    List<AudCarSalesDTO> findAllRentedOnDate(Date date);
}
