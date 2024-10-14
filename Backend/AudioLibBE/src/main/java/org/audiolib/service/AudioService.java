package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.Book;
import org.audiolib.entity.Song;
import org.audiolib.repository.AudioRepository;
import org.audiolib.repository.BookRepository;
import org.audiolib.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudioService {
    private final AudioRepository audioRepository;
    private final SongRepository songRepository;
    private final BookRepository bookRepository;

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
