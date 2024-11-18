package org.audiolib.dto;

import org.audiolib.entity.AudioCarrier;
import org.audiolib.entity.BookView;
import org.audiolib.entity.Feedback;

import java.util.List;

public record BookPageDetailsDTO(BookView bookView, List<Feedback> feedbacks, List<AudioCarrier> carriers){
}
