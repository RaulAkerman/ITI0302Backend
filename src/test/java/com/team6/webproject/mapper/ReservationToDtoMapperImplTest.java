package com.team6.webproject.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationToDtoMapperImplTest {
    private final ReservationToDtoMapperImpl mapper = new ReservationToDtoMapperImpl();
    @Test
    void toDto() {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toDtoList() {
        assertNull(mapper.toDtoList(null));
    }
}