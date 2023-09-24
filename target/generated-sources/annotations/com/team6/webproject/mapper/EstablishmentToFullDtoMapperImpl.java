package com.team6.webproject.mapper;

import com.team6.webproject.dto.get.FullEstablishmentDto;
import com.team6.webproject.repository.establishment.Establishment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T17:04:48+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class EstablishmentToFullDtoMapperImpl implements EstablishmentToFullDtoMapper {

    @Override
    public List<FullEstablishmentDto> toDtoList(List<Establishment> establishmentList) {
        if ( establishmentList == null ) {
            return null;
        }

        List<FullEstablishmentDto> list = new ArrayList<FullEstablishmentDto>( establishmentList.size() );
        for ( Establishment establishment : establishmentList ) {
            list.add( establishmentToFullEstablishmentDto( establishment ) );
        }

        return list;
    }

    protected FullEstablishmentDto establishmentToFullEstablishmentDto(Establishment establishment) {
        if ( establishment == null ) {
            return null;
        }

        FullEstablishmentDto fullEstablishmentDto = new FullEstablishmentDto();

        fullEstablishmentDto.setId( establishment.getId() );
        fullEstablishmentDto.setName( establishment.getName() );

        return fullEstablishmentDto;
    }
}
