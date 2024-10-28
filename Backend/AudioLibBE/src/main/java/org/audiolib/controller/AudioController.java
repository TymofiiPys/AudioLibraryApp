package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.BookDTO;
import org.audiolib.dto.SongDTO;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.service.AudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SongDTO[]> getSongs() {
        return ResponseEntity.ok(audioService.getAllSongs().toArray(new SongDTO[0]));
    }

    @GetMapping("/books")
    public ResponseEntity<BookDTO[]> getBooks() {
        return ResponseEntity.ok(audioService.getAllBooks().toArray(new BookDTO[0]));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongDTO> getSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(audioService.getSong(id));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(audioService.getBook(id));
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable(name = "id") Long id) {
        audioService.deleteSong(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id") Long id) {
        audioService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/songs")
    public ResponseEntity<SongDTO> updateSong(SongDTO songDTO) {
        return ResponseEntity.ok(audioService.updateSong(songDTO.getSong(), songDTO.getAudio()));
    }

    @PutMapping("/books")
    public ResponseEntity<BookDTO> updateBook(BookDTO bookDTO) {
        return ResponseEntity.ok(audioService.updateBook(bookDTO.getBook(), bookDTO.getAudio()));
    }
}
