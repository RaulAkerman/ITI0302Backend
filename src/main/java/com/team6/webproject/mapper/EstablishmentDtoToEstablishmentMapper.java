package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import com.team6.webproject.repository.establishment.Establishment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstablishmentDtoToEstablishmentMapper {

    Establishment toEstablishment(AddEstablishmentDto establishmentDto);
}
