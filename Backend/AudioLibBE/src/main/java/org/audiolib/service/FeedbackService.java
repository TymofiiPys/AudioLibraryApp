package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.Feedback;
import org.audiolib.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository repository;

    public Feedback saveFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public List<Feedback> getFeedback() {
        return repository.findAll();
    }

    public Feedback getFeedback(Long id) {
        return repository.findById(id).get();
    }

    public List<Feedback> getFeedbacksByAudio(Long audioId) {
        return repository.findAllByAudioId(audioId);
    }

    public Feedback updateFeedback(Feedback feedback) {
        Optional<Feedback> optFeedback = repository.findById(feedback.getId());
        if(optFeedback.isPresent()) {
            return repository.save(feedback);
        }
        return null;
    }

    public void deleteFeedback(Long id) {
        repository.deleteById(id);
    }
}
