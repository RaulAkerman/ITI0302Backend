package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.repository.reservation.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationDtoToReservationMapper {
    @Mapping(target = "establishment", ignore = true)
    @Mapping(target = "user", ignore = true)
    Reservation toReservation(ReservationDto reservationDto);
}
