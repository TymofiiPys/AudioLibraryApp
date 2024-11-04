package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.service.AudCarIndicatorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@RequiredArgsConstructor
public class AudCarIndicatorsController {
    private final AudCarIndicatorsService service;

    @PostMapping
    public ResponseEntity<Void> createInvEntry() {

        return ResponseEntity.ok().build();
    }
}
