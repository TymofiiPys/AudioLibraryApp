package org.audiolib.repository;

import org.audiolib.entity.SongView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongViewRepository extends JpaRepository<SongView, Long> {
    List<SongView> findByNameStartingWith(String name);
}
