package org.audiolib.dto;

import lombok.Data;
import org.audiolib.entity.Audio;
import org.audiolib.entity.Song;

public record SongDTO(Audio audio, Song song) {
}
