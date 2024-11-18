package org.audiolib.dto;

import org.audiolib.entity.Audio;
import org.audiolib.entity.Book;

public record BookDTO(Audio audio, Book book) {
}
