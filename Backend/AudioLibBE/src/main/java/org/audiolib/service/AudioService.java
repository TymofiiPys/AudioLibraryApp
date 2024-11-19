package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.BookDTO;
import org.audiolib.dto.BookPageDetailsDTO;
import org.audiolib.dto.SongDTO;
import org.audiolib.dto.SongPageDetailsDTO;
import org.audiolib.entity.*;
import org.audiolib.repository.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AudioService {
    private final AudioRepository audioRepository;
    private final SongRepository songRepository;
    private final BookRepository bookRepository;
    private final BookViewRepository bvRepo;
    private final SongViewRepository svRepo;
    private final FeedbackRepository feedbackRepository;
    private final CarrierRepository carrierRepository;

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

    public List<BookView> getAllBooks(){
        return bvRepo.findAll();
    }

    public List<SongView> getAllSongs(){
        return svRepo.findAll();
    }
    public SongPageDetailsDTO getSong(Long id) {
        SongView songView = svRepo.findById(id).get();
        List<Feedback> feedbacks = feedbackRepository.findAllByAudioId(songView.getId());
        List<AudioCarrier> carriers = carrierRepository.findAllByAudioId(songView.getId());
        return new SongPageDetailsDTO(songView, feedbacks.toArray(new Feedback[0]), carriers.toArray(new AudioCarrier[0]));
    }

    public BookPageDetailsDTO getBook(Long id) {
        BookView bookView = bvRepo.findById(id).get();
        List<Feedback> feedbacks = feedbackRepository.findAllByAudioId(bookView.getId());
        List<AudioCarrier> carriers = carrierRepository.findAllByAudioId(bookView.getId());
        return new BookPageDetailsDTO(bookView, feedbacks.toArray(new Feedback[0]), carriers.toArray(new AudioCarrier[0]));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
        audioRepository.deleteById(id);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        audioRepository.deleteById(id);
    }

    public SongDTO updateSong(Song song, Audio audio) {
        Audio audioNew = audioRepository.save(audio);
        Song songNew = songRepository.save(song);
        return new SongDTO(audioNew, songNew);
    }

    public BookDTO updateBook(Book book, Audio audio) {
        Audio audioNew = audioRepository.save(audio);
        Book bookNew = bookRepository.save(book);
        return new BookDTO(audioNew, bookNew);
    }

    public List<SongView> getSongsByName(String name) {
        return svRepo.findByNameStartingWith(name);
    }

    public List<BookView> getBooksByName(String name) {
        return bvRepo.findByNameStartingWith(name);
    }
}
