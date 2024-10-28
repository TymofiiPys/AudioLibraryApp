package org.audiolib.repository;

import org.audiolib.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long>{
    @Query(value = "INSERT INTO audio (name, year, duration, date_added) VALUES (?1, ?2, CAST(?3 AS interval), ?4) RETURNING id", nativeQuery = true)
    Long save(String name, int year, String duration, Date date);
    List<Audio> findAllByIdIn(List<Long> id);
}
