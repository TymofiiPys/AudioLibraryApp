package org.audiolib.repository;

import org.audiolib.entity.AudCarInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudCarInventoryRepository extends JpaRepository<AudCarInventory, Long> {
}
