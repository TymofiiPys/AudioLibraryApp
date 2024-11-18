package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.BookDTO;
import org.audiolib.dto.BookPageDetailsDTO;
import org.audiolib.dto.SongDTO;
import org.audiolib.dto.SongPageDetailsDTO;
import org.audiolib.entity.Book;
import org.audiolib.entity.BookView;
import org.audiolib.entity.Song;
import org.audiolib.entity.SongView;
import org.audiolib.service.AudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Endpoints for audio CRUD operations
 */
@RestController
@RequiredArgsConstructor
public class AudioController {
    private final AudioService audioService;

    @PostMapping("/songs")
    public ResponseEntity<Song> addSong(@RequestBody SongDTO songDTO) {
        Song savedSong = audioService.saveSong(songDTO.song(), songDTO.audio());
        return ResponseEntity.ok(savedSong);
        // TODO: also return audio info
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book savedBook = audioService.saveBook(bookDTO.book(), bookDTO.audio());
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping("/songs")
    public ResponseEntity<SongView[]> getSongs(@RequestParam(name = "name", required = false) Optional<String> name) {
        if(name.isPresent()){
            List<SongView> result = audioService.getSongsByName(name.get());
            if(result.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result.toArray(new SongView[0]));
        } else {
            return ResponseEntity.ok(audioService.getAllSongs().toArray(new SongView[0]));
        }
    }

    @GetMapping("/books")
    public ResponseEntity<BookView[]> getBooks(@RequestParam(name = "name", required = false) Optional<String> name) {
        if(name.isPresent()){
            List<BookView> result = audioService.getBooksByName(name.get());
            if(result.isEmpty())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result.toArray(new BookView[0]));
        } else {
            return ResponseEntity.ok(audioService.getAllBooks().toArray(new BookView[0]));
        }
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongPageDetailsDTO> getSong(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(audioService.getSong(id));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookPageDetailsDTO> getBook(@PathVariable(name = "id") Long id) {
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
        return ResponseEntity.ok(audioService.updateSong(songDTO.song(), songDTO.audio()));
    }

    @PutMapping("/books")
    public ResponseEntity<BookDTO> updateBook(BookDTO bookDTO) {
        return ResponseEntity.ok(audioService.updateBook(bookDTO.book(), bookDTO.audio()));
    }
}
