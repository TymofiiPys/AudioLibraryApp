package org.audiolib.repository;

import org.audiolib.entity.AudCarInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AudCarInventoryRepository extends JpaRepository<AudCarInventory, Long> {
    List<AudCarInventory> findAllByDate(Date date);
}
