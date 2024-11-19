package org.audiolib.repository;

import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.dto.GenreStatsDTO;
import org.audiolib.entity.AudCarSales;
import org.audiolib.entity.Transact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.sql.Date;
import java.util.List;

public interface TransactRepository extends JpaRepository<Transact, Long> {
    @Query(nativeQuery = true)
    List<AudCarSalesDTO> findAllRentedOnDate(Date date);

    @Query(value = "SELECT a.genre, CAST(0 as bit), sum() " +
            "FROM songs a " +
            "WHERE a.id in " +
            "(SELECT ac.audio_id, count(*) as audio_active_rents " +
            "FROM transact t INNER JOIN" +
            " audio_carriers ac ON t.audio_carrier_id = ac.id " +
            "WHERE ?1 between t.date_rented and t.date_end_of_rent " +
            "GROUP BY ac.audio_id)" +
            "GROUP BY a.genre",
    nativeQuery = true)
    List<GenreStatsDTO> genreStatsByDate(Date date);
}
