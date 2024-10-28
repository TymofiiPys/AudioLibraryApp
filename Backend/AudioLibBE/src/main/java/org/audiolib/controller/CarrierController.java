package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.CarrierDTO;
import org.audiolib.service.CarrierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarrierController {
    private final CarrierService carrierService;
    @GetMapping("/carriers")
    public ResponseEntity<CarrierDTO[]> getAllCarriers() {
        return ResponseEntity.ok(carrierService.getAllCarriers().toArray(new CarrierDTO[0]));
    }

    @GetMapping("/carriers/audio/{id}")
    public ResponseEntity<CarrierDTO[]> getCarriersByAudio(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carrierService.getCarriersByAudio(id).toArray(new CarrierDTO[0]));
    }

    @GetMapping("/carriers/{id}")
    public ResponseEntity<CarrierDTO> getCarrier(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(carrierService.getCarrier(id));
    }
}
