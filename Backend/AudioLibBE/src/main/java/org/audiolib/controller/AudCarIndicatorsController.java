package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.entity.AudCarInventory;
import org.audiolib.service.AudCarIndicatorsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequiredArgsConstructor
public class AudCarIndicatorsController {
    private final AudCarIndicatorsService service;

    @PostMapping("/inventory")
    public ResponseEntity<AudCarInventory[]> createInvEntry() {
        return ResponseEntity.ok(service.createInvEntry().toArray(new AudCarInventory[0]));
    }

    @GetMapping("/inventory/{date}")
    public ResponseEntity<AudCarInventory[]> createInvEntry(@PathVariable Date date) {
        return ResponseEntity.ok(service.getInvByDate(date).toArray(new AudCarInventory[0]));
    }
}
