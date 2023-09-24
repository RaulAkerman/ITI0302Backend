package com.team6.webproject.mapper;

import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.repository.reservation.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationToDtoMapper {
    @Mapping(target = "establishment", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(source = "id", target = "id")
    @Mapping(target = "bookedCustomer", ignore = true)
    GetReservationDto toDto(Reservation reservation);

     List<GetReservationDto> toDtoList(List<Reservation> reservations);
}
