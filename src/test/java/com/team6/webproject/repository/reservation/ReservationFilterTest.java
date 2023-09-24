package com.team6.webproject.repository.reservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationFilterTest {

    @Test
    void get() {
        ReservationFilter reservationFilter = new ReservationFilter();
        reservationFilter.setDate("2020");
        reservationFilter.setEstablishment("Estab");
        reservationFilter.setOrderBy("estab");
        reservationFilter.setOrder("ASC");

        assertEquals("2020", reservationFilter.getDate());
        assertEquals("Estab", reservationFilter.getEstablishment());
        assertEquals("estab", reservationFilter.getOrderBy());
        assertEquals("ASC", reservationFilter.getOrder());
    }
}