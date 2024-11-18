package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.AudioRentStatDTO;
import org.audiolib.entity.BookListens;
import org.audiolib.entity.SongListens;
import org.audiolib.repository.BookListensRepository;
import org.audiolib.repository.HistoryRepository;
import org.audiolib.repository.SongListensRepository;
import org.audiolib.repository.TransactRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final HistoryRepository hRepository;
    private final TransactRepository tRepository;
    private final SongListensRepository slRepo;
    private final BookListensRepository blRepo;

    public List<SongListens> getPopularSongs(Integer amt) {
        return slRepo.findAll(PageRequest.of(0, amt)).stream().toList();
    }

    public List<BookListens> getPopularBooks(Integer amt) {
        return blRepo.findAll(PageRequest.of(0, amt)).stream().toList();
    }

    public AudioRentStatDTO getSongRentStat(Long id) {
        return tRepository.getRentStatSongById(id);
    }

    public AudioRentStatDTO getBookRentStat(Long id) {
        return tRepository.getRentStatBookById(id);
    }
}
