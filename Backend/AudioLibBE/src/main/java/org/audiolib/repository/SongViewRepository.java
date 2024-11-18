package org.audiolib.repository;

import org.audiolib.entity.SongView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongViewRepository extends JpaRepository<SongView, Long> {
}
