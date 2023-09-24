package com.team6.webproject.repository.establishment;

import com.team6.webproject.repository.reservation.ReservationFilter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class EstablishmentFilterTest {

    @Mock


    @InjectMocks
    private EstablishmentFilter establishmentFilter;

    @Test
    void get() {
        EstablishmentFilter establishmentFilter1 = new EstablishmentFilter();
        establishmentFilter1.setOrderBy("estab");
        establishmentFilter1.setOrder("ASC");
        establishmentFilter1.setName("name");

        EstablishmentFilter establishmentFilter = new EstablishmentFilter("name", "ASC", "estab");


        assertEquals("name", establishmentFilter.getName());
        assertEquals("estab", establishmentFilter.getOrderBy());
        assertEquals("ASC", establishmentFilter.getOrder());

    }
}