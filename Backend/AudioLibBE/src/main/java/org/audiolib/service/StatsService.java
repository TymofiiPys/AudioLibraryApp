package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.AudioRentStatDTO;
import org.audiolib.dto.PopularAudioDTO;
import org.audiolib.repository.HistoryRepository;
import org.audiolib.repository.TransactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final HistoryRepository hRepository;
    private final TransactRepository tRepository;

    public List<PopularAudioDTO> getPopularSongs(Integer amt) {
        return hRepository.getPopularAudio(amt, true);
    }

    public List<PopularAudioDTO> getPopularBooks(Integer amt) {
        return hRepository.getPopularAudio(amt, false);
    }

    public AudioRentStatDTO getSongRentStat(Long id) {
        return tRepository.getRentStatSongById(id);
    }

    public AudioRentStatDTO getBookRentStat(Long id) {
        return tRepository.getRentStatBookById(id);
    }

    public
}
