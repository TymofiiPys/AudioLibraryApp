package org.audiolib.dto;

import lombok.Data;
import org.audiolib.entity.Audio;
import org.audiolib.entity.Book;

@Data
public class BookDTO {
    private final Audio audio;
    private final Book book;
}
