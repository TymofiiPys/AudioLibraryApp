package org.audiolib.service;

import lombok.RequiredArgsConstructor;
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

    
}
