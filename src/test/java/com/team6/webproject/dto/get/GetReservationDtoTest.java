package com.team6.webproject.dto.get;

import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.repository.establishment.Establishment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetReservationDtoTest {

    @Test
    void getId() {
        GetReservationDto getReservationDto = new GetReservationDto();
        getReservationDto.setEstablishment("Test");
        getReservationDto.setBookedCustomer("Customer");
        getReservationDto.setStatus("available");
        getReservationDto.setId(1);
        getReservationDto.setDate("2023");
        getReservationDto.setUsername("User");

        assertEquals("Test", getReservationDto.getEstablishment());
        assertEquals("Customer", getReservationDto.getBookedCustomer());
        assertEquals("available", getReservationDto.getStatus());
        assertEquals(1, getReservationDto.getId());
        assertEquals("2023", getReservationDto.getDate());
        assertEquals("User", getReservationDto.getUsername());
    }
}