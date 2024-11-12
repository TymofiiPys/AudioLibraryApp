package org.audiolib.repository;

import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.dto.AudioRentStatDTO;
import org.audiolib.entity.AudCarSales;
import org.audiolib.entity.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TransactRepository extends JpaRepository<Transact, Long> {
    @Query(value = "SELECT t.audioCarrierId as carrier_id, COUNT(*) as quantity, ?1 as _date" +
            " from Transact t " +
            " where ?1 between t.dateRented and t.dateEndOfRent" +
            " group by t.audioCarrierId")
    List<AudCarSalesDTO> findAllRentedOnDate(Date date);

    @Query(value = "SELECT a.name as name, (SELECT ar.name FROM artists ar WHERE ar.id = (SELECT s.artist FROM songs s where s.id = a.id)) AS creator," +
            "(SELECT avg(t.date_end_of_rent - t.date_rented) as avgRent, count(*) as rents " +
            "FROM transact t " +
            "WHERE t.audio_carrier_id IN (" +
            "SELECT ac.id " +
            "FROM audio_carriers ac " +
            "WHERE ac.audio_id = a.id" +
            ")) " +
            "FROM audio a " +
            "WHERE a.id = ?1 ",
            nativeQuery = true)
    AudioRentStatDTO getRentStatSongById(Long id);

    @Query(value = "SELECT a.name as name, (SELECT au.name FROM book_authors au WHERE au.id = (SELECT b.author FROM books b where b.id = a.id)) AS creator," +
            "(SELECT avg(t.date_end_of_rent - t.date_rented) as avgRent, count(*) as rents " +
            "FROM transact t " +
            "WHERE t.audio_carrier_id IN (" +
            "SELECT ac.id " +
            "FROM audio_carriers ac " +
            "WHERE ac.audio_id = a.id" +
            ")) " +
            "FROM audio a " +
            "WHERE a.id = ?1 ",
            nativeQuery = true)
    AudioRentStatDTO getRentStatBookById(Long id);
}
