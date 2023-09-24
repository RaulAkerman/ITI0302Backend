package com.team6.webproject.mapper;

import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.repository.reservation.Reservation;
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
public class ReservationToDtoMapperImpl implements ReservationToDtoMapper {

    @Override
    public GetReservationDto toDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        GetReservationDto getReservationDto = new GetReservationDto();

        getReservationDto.setId( reservation.getId() );
        getReservationDto.setDate( reservation.getDate() );
        getReservationDto.setStatus( reservation.getStatus() );

        return getReservationDto;
    }

    @Override
    public List<GetReservationDto> toDtoList(List<Reservation> reservations) {
        if ( reservations == null ) {
            return null;
        }

        List<GetReservationDto> list = new ArrayList<GetReservationDto>( reservations.size() );
        for ( Reservation reservation : reservations ) {
            list.add( toDto( reservation ) );
        }

        return list;
    }
}
