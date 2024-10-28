package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.CarrierDTO;
import org.audiolib.service.CarrierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;
    @GetMapping("/carriers")
    public ResponseEntity<CarrierDTO[]> getAllCarriers() {
        return ResponseEntity.ok()
    }
}
