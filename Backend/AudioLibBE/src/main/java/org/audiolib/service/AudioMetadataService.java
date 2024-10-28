package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.MetadataDTO;
import org.audiolib.entity.Artists;
import org.audiolib.entity.BookAuthor;
import org.audiolib.entity.GenreBooks;
import org.audiolib.entity.GenreMusic;
import org.audiolib.repository.ArtistRepository;
import org.audiolib.repository.BookAuthorRepository;
import org.audiolib.repository.GenreBookRepository;
import org.audiolib.repository.GenreMusicRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioMetadataService {
    private final GenreBookRepository gBookRepository;
    private final GenreMusicRepository gMusicRepository;
    private final ArtistRepository artistRepository;
    private final BookAuthorRepository bookAuthorRepository;

    public String getGenreBookName(Integer id) {
        return gBookRepository.findById(id).get().getName();
    }

    public String getGenreMusicName(Integer id) {
        return gMusicRepository.findById(id).get().getName();
    }

    public String getArtistName(Integer id) {
        return artistRepository.findById(id).get().getName();
    }

    public String getBookAuthorName(Integer id) {
        return bookAuthorRepository.findById(id).get().getName();
    }

    public MetadataDTO saveArtist(Artists artist) {
        Artists artistNew = artistRepository.save(artist);
        return new MetadataDTO(artistNew.getId(), artistNew.getName());
    }

    public MetadataDTO saveBookAuthor(BookAuthor author) {
       BookAuthor authorNew = bookAuthorRepository.save(author);
        return new MetadataDTO(authorNew.getId(), authorNew.getName());
    }

    public MetadataDTO saveGenreMusic(GenreMusic genre) {
        GenreMusic genreNew = gMusicRepository.save(genre);
        return new MetadataDTO(genreNew.getId(), genreNew.getName());
    }

    public MetadataDTO saveGenreBooks(GenreBooks genre) {
        GenreBooks genreNew = gBookRepository.save(genre);
        return new MetadataDTO(genreNew.getId(), genreNew.getName());
    }

    public MetadataDTO updateArtist(Artists artist) {
        Artists artistDB = artistRepository.findById(artist.getId()).get();
        artistDB.setName(artist.getName());
        Artists artistNew = artistRepository.save(artistDB);
        return new MetadataDTO(artistNew.getId(), artistNew.getName());
    }

    public MetadataDTO updateBookAuthor(BookAuthor author) {
        BookAuthor authorDB = bookAuthorRepository.findById(author.getId()).get();
        authorDB.setName(author.getName());
        BookAuthor authorNew = bookAuthorRepository.save(authorDB);
        return new MetadataDTO(authorNew.getId(), authorNew.getName());
    }

    public MetadataDTO updateGenreMusic(GenreMusic genre) {
        GenreMusic genreDB = gMusicRepository.findById(genre.getId()).get();
        genreDB.setName(genreDB.getName());
        GenreMusic genreNew = gMusicRepository.save(genreDB);
        return new MetadataDTO(genreNew.getId(), genreNew.getName());
    }

    public MetadataDTO updateGenreBooks(GenreBooks genre) {
        GenreBooks genreDB = gBookRepository.findById(genre.getId()).get();
        genreDB.setName(genreDB.getName());
        GenreBooks genreNew = gBookRepository.save(genreDB);
        return new MetadataDTO(genreNew.getId(), genreNew.getName());
    }

    public void deleteArtist(Integer id) {
        artistRepository.deleteById(id);
    }

    public void deleteBookAuthor(Integer id) {
        bookAuthorRepository.deleteById(id);
    }
    public void deleteGenreMusic(Integer id) {
        gMusicRepository.deleteById(id);
    }
    public void deleteGenreBooks(Integer id) {
        gBookRepository.deleteById(id);
    }
}
