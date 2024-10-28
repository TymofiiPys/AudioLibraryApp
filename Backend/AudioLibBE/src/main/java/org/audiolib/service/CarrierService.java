package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.repository.CarrierRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarrierService {
    private final CarrierRepository carrierRepository;


}
