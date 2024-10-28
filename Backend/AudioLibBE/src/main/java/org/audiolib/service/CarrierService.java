package org.audiolib.service;

import lombok.RequiredArgsConstructor;
import org.audiolib.dto.CarrierDTO;
import org.audiolib.entity.AudioCarriers;
import org.audiolib.mapper.CarrierToDTOMapper;
import org.audiolib.repository.CarrierRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrierService {
    private final CarrierRepository carrierRepository;
    private final CarrierToDTOMapper mapper = CarrierToDTOMapper.INSTANCE;

    public List<CarrierDTO> getAllCarriers() {
        List<AudioCarriers> carriers = carrierRepository.findAll();
        return carriers.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public List<CarrierDTO> getCarriersByAudio(Long id) {
        List<AudioCarriers> carriers = carrierRepository.findAllByAudioId(id);
        return carriers.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public CarrierDTO getCarrier(Long id) {
        AudioCarriers carrier = carrierRepository.findById(id).get();
        return mapper.toDTO(carrier);
    }

    public CarrierDTO saveCarrier(CarrierDTO carrierDTO){
        AudioCarriers carrier = mapper.toCarrier(carrierDTO);
        ExampleMatcher carrierMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
        if(!carrierRepository.exists(Example.of(carrier, carrierMatcher))) {
            return mapper.toDTO(carrierRepository.save(carrier));
        }
        return null;
    }

    public CarrierDTO setNewAmt(CarrierDTO carrierDTO) {
        carrierRepository.updateCarrierAmt(carrierDTO.id(), carrierDTO.amtAvailable());
        return carrierDTO;
    }
}
