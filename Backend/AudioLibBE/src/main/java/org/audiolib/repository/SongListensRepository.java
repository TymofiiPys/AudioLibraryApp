package org.audiolib.repository;

import org.audiolib.entity.SongListens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongListensRepository extends JpaRepository<SongListens, Long> {
}

