package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.MetadataDTO;
import org.audiolib.entity.Artists;
import org.audiolib.entity.BookAuthor;
import org.audiolib.entity.GenreBooks;
import org.audiolib.entity.GenreMusic;
import org.audiolib.service.AudioMetadataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AudioMetadataController {
    private final AudioMetadataService amService;

    @GetMapping("/artist/{id}")
    public ResponseEntity<MetadataDTO> getArtist(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(new MetadataDTO(id, amService.getArtistName(id)));
    }

    @GetMapping("/bookauthor/{id}")
    public ResponseEntity<MetadataDTO> getBookAuthor(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(new MetadataDTO(id, amService.getBookAuthorName(id)));
    }

    @GetMapping("/genmus/{id}")
    public ResponseEntity<MetadataDTO> getGenreMusic(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(new MetadataDTO(id, amService.getGenreMusicName(id)));
    }

    @GetMapping("/genbook/{id}")
    public ResponseEntity<MetadataDTO> getGenreBook(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(new MetadataDTO(id, amService.getGenreBookName(id)));
    }

    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/bookauthor/{id}")
    public ResponseEntity<Void> deleteBookAuthor(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/genmus/{id}")
    public ResponseEntity<Void> deleteGenreMusic(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/genbook/{id}")
    public ResponseEntity<Void> deleteGenreBook(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/artist")
    public ResponseEntity<MetadataDTO> createArtist(@RequestBody Artists artist) {
        return ResponseEntity.ok(amService.saveArtist(artist));
    }

    @PostMapping("/bookauthor")
    public ResponseEntity<MetadataDTO> createBookAuthor(@RequestBody BookAuthor author) {
        return ResponseEntity.ok(amService.saveBookAuthor(author));
    }

    @PostMapping("/genmus")
    public ResponseEntity<MetadataDTO> createGenreMusic(@RequestBody GenreMusic genre) {
        return ResponseEntity.ok(amService.saveGenreMusic(genre));
    }

    @PostMapping("/genbook")
    public ResponseEntity<MetadataDTO> createGenreBook(@RequestBody GenreBooks genre) {
        return ResponseEntity.ok(amService.saveGenreBooks(genre));
    }

    @PutMapping("/artist")
    public ResponseEntity<MetadataDTO> updateArtist(@RequestBody Artists artist) {
        return ResponseEntity.ok(amService.updateArtist(artist));
    }

    @PutMapping("/bookauthor")
    public ResponseEntity<MetadataDTO> updateBookAuthor(@RequestBody BookAuthor author) {
        return ResponseEntity.ok(amService.updateBookAuthor(author));
    }

    @PutMapping("/genmus")
    public ResponseEntity<MetadataDTO> updateGenreMusic(@RequestBody GenreMusic genre) {
        return ResponseEntity.ok(amService.updateGenreMusic(genre));
    }

    @PutMapping("/genbook")
    public ResponseEntity<MetadataDTO> updateGenreBook(@RequestBody GenreBooks genre) {
        return ResponseEntity.ok(amService.updateGenreBooks(genre));
    }
}
