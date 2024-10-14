package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.SongReceiveDTO;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.service.AudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints for audio CRUD operations
 */
@RestController
@RequiredArgsConstructor
public class AudioController {
    private final AudioService audioService;

    @PostMapping("/songs")
    public ResponseEntity<Song> addSong(@RequestBody SongReceiveDTO songDTO) {
        Song savedSong = audioService.saveSong(songDTO.getSong(), songDTO.getAudio());
        return ResponseEntity.ok(savedSong);
        // TODO: also return audio info
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = audioService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }
}
