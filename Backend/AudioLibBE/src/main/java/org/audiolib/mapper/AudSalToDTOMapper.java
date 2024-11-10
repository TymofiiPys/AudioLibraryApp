package org.audiolib.mapper;

import org.audiolib.dto.AudCarSalesDTO;
import org.audiolib.entity.AudCarSales;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AudSalToDTOMapper {
    AudSalToDTOMapper INSTANCE = Mappers.getMapper(AudSalToDTOMapper.class);
    AudCarSales fromDTO(AudCarSalesDTO dto);
}
