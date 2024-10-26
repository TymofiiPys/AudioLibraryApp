package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.Audio;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.repository.AudioRepository;
import org.audiolib.repository.BookRepository;
import org.audiolib.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

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
}
