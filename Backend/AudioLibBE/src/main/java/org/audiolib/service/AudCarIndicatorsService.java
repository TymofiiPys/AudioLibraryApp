package org.audiolib.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.entity.AudCarInventory;
import org.audiolib.entity.AudCarSales;
import org.audiolib.entity.AudioCarriers;
import org.audiolib.entity.Transact;
import org.audiolib.mapper.AudSalToDTOMapper;
import org.audiolib.repository.AudCarInventoryRepository;
import org.audiolib.repository.AudCarSalesRepository;
import org.audiolib.repository.CarrierRepository;
import org.audiolib.repository.TransactRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AudCarIndicatorsService {
    private final AudCarInventoryRepository invRepository;
    private final AudCarSalesRepository salesRepository;
    private final CarrierRepository carrierRepository;
    private final TransactRepository transactRepository;
    private final AudSalToDTOMapper mapper = AudSalToDTOMapper.INSTANCE;
    public void createInvEntry(){
        List<AudioCarriers> audioCarriers = carrierRepository.findAll();
        List<AudCarInventory> carInv = new ArrayList<>();
        Date today = Date.valueOf(LocalDate.now());
        for (var carrier : audioCarriers) {
            AudCarInventory carInvRecord = new AudCarInventory();
            carInvRecord.setCarrierId(carrier.getId());
            carInvRecord.setDate(today);
            carInvRecord.setQuantity(carrier.getAmtAvailable());
        }
        List<AudCarInventory> carInvSaved = invRepository.saveAll(carInv);
    }
    public void createSalEntry(){
        List<AudCarSalesDTO> transactsPrevDate = transactRepository.findAllRentedOnDate(
                Date.valueOf(
                        LocalDate.now().minusDays(1)
                )
        );
        List<AudCarSales> salEntries = transactsPrevDate.stream().map(mapper::fromDTO).toList();
        List<AudCarSales> salSaved = salesRepository.saveAll(salEntries);
    }
}
