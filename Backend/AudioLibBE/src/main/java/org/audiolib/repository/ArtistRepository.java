package org.audiolib.repository;

import org.audiolib.entity.Artists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artists, Integer> {
}
