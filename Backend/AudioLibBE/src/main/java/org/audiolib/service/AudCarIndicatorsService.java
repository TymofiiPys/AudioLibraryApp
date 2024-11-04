package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.repository.AudCarInventoryRepository;
import org.audiolib.repository.AudCarSalesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AudCarIndicatorsService {
    private final AudCarInventoryRepository invRepository;
    private final AudCarSalesRepository salesRepository;
    public void createInvEntry(){}
    public void createSalEntry(){}
}
