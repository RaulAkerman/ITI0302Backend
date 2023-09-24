package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.repository.reservation.Reservation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T17:04:48+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ReservationDtoToReservationMapperImpl implements ReservationDtoToReservationMapper {

    @Override
    public Reservation toReservation(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setDate( reservationDto.getDate() );
        reservation.setStatus( reservationDto.getStatus() );

        return reservation;
    }
}
