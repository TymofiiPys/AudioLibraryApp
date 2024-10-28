package org.audiolib.repository;

import org.audiolib.entity.AudioCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<AudioCarriers, Long> {
    List<AudioCarriers> findAllByAudioId(Long id);
}
