package org.audiolib.repository;

import org.audiolib.entity.GenreMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreMusicRepository extends JpaRepository<GenreMusic, Integer> {}
