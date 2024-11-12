package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.PopularAudioDTO;
import org.audiolib.service.StatsService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatsService service;

    @GetMapping(value = "/top_songs")
    public ResponseEntity<PopularAudioDTO[]> getPopularSongs(@RequestParam(name = "top") Integer amt) {
        return ResponseEntity.ok(service.getPopularSongs(amt).toArray(new PopularAudioDTO[0]));
    }

    @GetMapping(value = "/top_books")
    public ResponseEntity<PopularAudioDTO[]> getPopularBooks(@RequestParam(name = "top") Integer amt) {
        return ResponseEntity.ok(service.getPopularBooks(amt).toArray(new PopularAudioDTO[0]));
    }
}
