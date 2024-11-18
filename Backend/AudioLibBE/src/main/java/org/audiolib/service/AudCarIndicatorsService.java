package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.dto.GenreStatsDTO;
import org.audiolib.entity.AudCarInventory;
import org.audiolib.entity.AudCarSales;
import org.audiolib.entity.AudioCarrier;
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
    public List<AudCarInventory> createInvEntry(){
        List<AudioCarrier> audioCarriers = carrierRepository.findAllByCarrierIn(List.of(
                AudioCarrier.Carriers.cd, AudioCarrier.Carriers.vinyl
        ));
        List<AudCarInventory> carInv = new ArrayList<>();
        Date today = Date.valueOf(LocalDate.now());
        for (var carrier : audioCarriers) {
            AudCarInventory carInvRecord = new AudCarInventory();
            carInvRecord.setCarrierId(carrier.getId());
            carInvRecord.setDate(today);
            carInvRecord.setQuantity(carrier.getAmtAvailable());
            carInv.add(carInvRecord);
        }
        List<AudCarInventory> carInvSaved = invRepository.saveAll(carInv);
        return carInvSaved;
    }

    public List<AudCarInventory> getInvByDate(Date date) {
        return invRepository.findAllByDate(date);
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

    public void createGenreEntry() {
        List<GenreStatsDTO> dtos = transactRepository.genreStatsByDate(Date.valueOf(LocalDate.now()));
    }
}
