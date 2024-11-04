package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.Feedback;
import org.audiolib.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService service;
    @PostMapping("/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(service.saveFeedback(feedback));
    }

    @GetMapping("/feedback")
    public ResponseEntity<Feedback[]> getFeedbacks() {
        return ResponseEntity.ok(service.getFeedback().toArray(new Feedback[0]));
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getFeedback(id));
    }

    @GetMapping("/feedback/a/{id}")
    public ResponseEntity<Feedback[]> getFeedbacksByAudio(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getFeedbacksByAudio(id).toArray(new Feedback[0]));
    }

    @PutMapping("/feedback")
    public ResponseEntity<Feedback> updateFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(service.updateFeedback(feedback));
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable(name = "id") Long id) {
        service.deleteFeedback(id);
        return ResponseEntity.ok().build();
    }
}
