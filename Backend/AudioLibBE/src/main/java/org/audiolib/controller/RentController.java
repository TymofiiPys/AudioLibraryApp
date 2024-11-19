package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.MetadataDTO;
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

    /* FIXME:
    Metadatadto is not a good way of response here
     */
    @PostMapping("/rent")
    public ResponseEntity<MetadataDTO> createRent(@RequestBody RentReceiveDTO rentReceiveDTO) {
        service.createRent(rentReceiveDTO);
        return ResponseEntity.ok(new MetadataDTO(1, "yay"));
    }
}
