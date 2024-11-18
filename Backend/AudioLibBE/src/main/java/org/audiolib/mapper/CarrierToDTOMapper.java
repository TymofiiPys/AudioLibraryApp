package org.audiolib.mapper;

import org.audiolib.dto.CarrierDTO;
import org.audiolib.entity.AudioCarrier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarrierToDTOMapper {
    CarrierToDTOMapper INSTANCE = Mappers.getMapper(CarrierToDTOMapper.class);
    CarrierDTO toDTO(AudioCarrier carrier);
    AudioCarrier toCarrier(CarrierDTO carrierDTO);
}
