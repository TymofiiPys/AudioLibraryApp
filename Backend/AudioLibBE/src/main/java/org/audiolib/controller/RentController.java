package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.RentReceiveDTO;
import org.audiolib.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentController {
    private final RentService service;

    @PostMapping("/rent")
    public ResponseEntity<Void> createRent(@RequestBody RentReceiveDTO rentReceiveDTO) {
        service.createRent(rentReceiveDTO);
        return ResponseEntity.ok().build();
    }
}
