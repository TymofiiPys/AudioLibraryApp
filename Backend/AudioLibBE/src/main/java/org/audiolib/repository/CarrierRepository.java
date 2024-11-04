package org.audiolib.repository;

import org.audiolib.entity.AudioCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<AudioCarriers, Long> {
    List<AudioCarriers> findAllByAudioId(Long id);
    @Query(value = "UPDATE audio_carriers SET amt_available = ?2 WHERE id = ?1", nativeQuery = true)
    void updateCarrierAmt(Long id, Integer amtAvailable);
    @Query(value = "SELECT audio_id FROM audio_carriers WHERE id = ?1", nativeQuery = true)
    Long findAudioIdByCarrier(Long carrierId);
}
