package com.team6.webproject.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDtoToReservationMapperImplTest {
    private final ReservationDtoToReservationMapperImpl mapper = new ReservationDtoToReservationMapperImpl();
    @Test
    void toReservation() {
        assertNull(mapper.toReservation(null));
    }
}