package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.BookDTO;
import org.audiolib.dto.SongDTO;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.service.AudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Song> addSong(@RequestBody SongDTO songDTO) {
        Song savedSong = audioService.saveSong(songDTO.getSong(), songDTO.getAudio());
        return ResponseEntity.ok(savedSong);
        // TODO: also return audio info
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book savedBook = audioService.saveBook(bookDTO.getBook(), bookDTO.getAudio());
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/songs")
    public ResponseEntity<Song[]> getSongs(){
        return null;
    }
}
