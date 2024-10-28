package org.audiolib.controller;

import lombok.RequiredArgsConstructor;
import org.audiolib.service.AudioMetadataService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AudioMetadataController {
    private final AudioMetadataService amService;
}
