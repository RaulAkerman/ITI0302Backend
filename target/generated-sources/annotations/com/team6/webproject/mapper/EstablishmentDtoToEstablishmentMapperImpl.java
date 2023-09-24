package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import com.team6.webproject.repository.establishment.Establishment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T17:04:48+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class EstablishmentDtoToEstablishmentMapperImpl implements EstablishmentDtoToEstablishmentMapper {

    @Override
    public Establishment toEstablishment(AddEstablishmentDto establishmentDto) {
        if ( establishmentDto == null ) {
            return null;
        }

        Establishment establishment = new Establishment();

        establishment.setName( establishmentDto.getName() );

        return establishment;
    }
}
