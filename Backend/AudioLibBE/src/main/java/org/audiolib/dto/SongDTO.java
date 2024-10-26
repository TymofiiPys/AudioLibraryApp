package org.audiolib.dto;

import lombok.Data;
import org.audiolib.entity.Audio;
import org.audiolib.entity.Song;

@Data
public class SongDTO {
    private final Audio audio;
    private final Song song;
}
