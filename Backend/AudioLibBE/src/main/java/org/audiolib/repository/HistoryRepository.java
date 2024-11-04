package org.audiolib.repository;

import org.audiolib.entity.History;
import org.audiolib.entity.HistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, HistoryPK>{
}
