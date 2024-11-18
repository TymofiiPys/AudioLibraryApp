package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.AudioRentStat;
import org.audiolib.entity.BookListens;
import org.audiolib.entity.SongListens;
import org.audiolib.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService service;

    @GetMapping(value = "/top_songs")
    public ResponseEntity<SongListens[]> getPopularSongs(@RequestParam(name = "top") Integer amt) {
        return ResponseEntity.ok(service.getPopularSongs(amt).toArray(new SongListens[0]));
    }

    @GetMapping(value = "/top_books")
    public ResponseEntity<BookListens[]> getPopularBooks(@RequestParam(name = "top") Integer amt) {
        return ResponseEntity.ok(service.getPopularBooks(amt).toArray(new BookListens[0]));
    }

    @GetMapping(value = "/rent_stat/{id}")
    public ResponseEntity<AudioRentStat> getAudioRentStat(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getAudioRentStat(id));
    }

//    @GetMapping(value = "/rent_stat_book/{id}")
//    public ResponseEntity<AudioRentStatDTO> getPopularBooks(@PathVariable(name = "id") Long id) {
//        return ResponseEntity.ok(service.getBookRentStat(id));
//    }
}
