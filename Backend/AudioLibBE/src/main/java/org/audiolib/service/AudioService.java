package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.BookDTO;
import org.audiolib.dto.SongDTO;
import org.audiolib.entity.Audio;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.repository.AudioRepository;
import org.audiolib.repository.BookRepository;
import org.audiolib.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AudioService {
    private final AudioRepository audioRepository;
    private final SongRepository songRepository;
    private final BookRepository bookRepository;

    private void addCurrentDate(Audio audio) {
        audio.setDateAdded(Date.valueOf(LocalDate.now()));
    }

    public Song saveSong(Song song, Audio audio) {
        addCurrentDate(audio);
        Long audioId = audioRepository.save(audio.getName(), audio.getYear(), audio.getDuration(), audio.getDateAdded());
        song.setId(audioId);
        songRepository.save(song);
        return song;
    }

    public Book saveBook(Book book, Audio audio) {
        addCurrentDate(audio);
        Long audioId = audioRepository.save(audio.getName(), audio.getYear(), audio.getDuration(), audio.getDateAdded());
        book.setId(audioId);
        bookRepository.save(book);
        return book;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<Audio> audio = audioRepository.findAllByIdIn(books.stream().map(Book::getId).collect(Collectors.toList()));
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            bookDTOS.add(new BookDTO(audio.get(i), books.get(i)));
        }
        return bookDTOS;
    }

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        List<Audio> audio = audioRepository.findAllByIdIn(songs.stream().map(Song::getId).collect(Collectors.toList()));
        List<SongDTO> songDTOS = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            songDTOS.add(new SongDTO(audio.get(i), songs.get(i)));
        }
        return songDTOS;
    }

    public SongDTO getSong(Long id) {
        Song song = songRepository.findById(id).get();
        Audio audio = audioRepository.findById(id).get();
        return new SongDTO(audio, song);
    }

    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id).get();
        Audio audio = audioRepository.findById(id).get();
        return new BookDTO(audio, book);
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
        audioRepository.deleteById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        audioRepository.deleteById(id);
    }
}
