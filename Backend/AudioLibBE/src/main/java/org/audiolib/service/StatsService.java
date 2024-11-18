package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.AudioRentStatDTO;
import org.audiolib.entity.AudioRentStat;
import org.audiolib.entity.BookListens;
import org.audiolib.entity.SongListens;
import org.audiolib.repository.*;
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
    private final AudioRentStatRepository arsRepo;

    public List<SongListens> getPopularSongs(Integer amt) {
        return slRepo.findAll(PageRequest.of(0, amt)).stream().toList();
    }

    public List<BookListens> getPopularBooks(Integer amt) {
        return blRepo.findAll(PageRequest.of(0, amt)).stream().toList();
    }

    public AudioRentStat getAudioRentStat(Long id) {
        return arsRepo.findById(id).get();
    }

//    public AudioRentStatDTO getBookRentStat(Long id) {
//        return tRepository.getRentStatBookById(id);
//    }
}
