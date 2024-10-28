package org.audiolib.repository;

import org.audiolib.entity.AudioCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<AudioCarriers, Long> {
}
