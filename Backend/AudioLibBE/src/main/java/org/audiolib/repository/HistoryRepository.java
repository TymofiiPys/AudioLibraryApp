package org.audiolib.repository;

import org.audiolib.entity.History;
import org.audiolib.entity.HistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, HistoryPK>{
}
