package org.audiolib.dto;

import org.audiolib.entity.AudioCarrier;
import org.audiolib.entity.Feedback;
import org.audiolib.entity.SongView;

import java.util.List;

public record SongPageDetailsDTO(SongView songView, List<Feedback> feedbacks, List<AudioCarrier> carriers){
}
