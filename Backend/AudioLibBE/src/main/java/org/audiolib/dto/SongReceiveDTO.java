package org.audiolib.dto;

import org.audiolib.entity.Audio;
import org.audiolib.entity.Song;

public record SongReceiveDTO(Audio audio, Song song) {
}
