package org.audiolib.repository;

import org.audiolib.entity.AudioRentStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRentStatRepository extends JpaRepository<AudioRentStat, Long> {
}
