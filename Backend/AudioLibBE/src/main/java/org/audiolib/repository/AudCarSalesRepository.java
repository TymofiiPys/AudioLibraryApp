package org.audiolib.repository;

import org.audiolib.entity.AudCarSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AudCarSalesRepository extends JpaRepository<AudCarSales, Long> {
    List<AudCarSales> findAllByDate(Date date);
}
