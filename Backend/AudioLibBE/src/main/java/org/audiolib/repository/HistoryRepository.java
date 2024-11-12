package org.audiolib.repository;

import org.audiolib.dto.PopularAudioDTO;
import org.audiolib.entity.History;
import org.audiolib.entity.HistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, HistoryPK>{
    @Query(value = "SELECT sum(h.amt_listened) as listened, " +
            "(SELECT a.name " +
            "FROM audio a " +
            "WHERE a.id = h.audio_id) as name, " +
            "CASE " +
            " WHEN ?2 THEN (SELECT ar.name FROM artists ar WHERE ar.id = (SELECT s.artist FROM songs s where s.id = h.audio_id)) " +
            "ELSE (SELECT au.name FROM book_authors au WHERE au.id = (SELECT b.author FROM books b where b.id = h.audio_id)) " +
            "END AS creator " +
            "from history h " +
            "group by h.audio_id " +
            "order by listened " +
            "LIMIT ?1", nativeQuery = true)
    List<PopularAudioDTO> getPopularAudio(Integer amt, boolean song);
}
