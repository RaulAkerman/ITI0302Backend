package com.team6.webproject.mapper;

import com.team6.webproject.dto.get.FullEstablishmentDto;
import com.team6.webproject.repository.establishment.Establishment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstablishmentToFullDtoMapper {

    List<FullEstablishmentDto> toDtoList(List<Establishment> establishmentList);
}
