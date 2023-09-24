package com.team6.webproject.mapper;

import com.team6.webproject.repository.establishment.Establishment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstablishmentToFullDtoMapperImplTest {
    private final EstablishmentToFullDtoMapperImpl mapper = new EstablishmentToFullDtoMapperImpl();

    @Test
    void toDtoList() {
        Establishment establishment1 = new Establishment();
        establishment1.setName("estab");

        List<Establishment> establishments = new ArrayList<>();
        establishments.add(establishment1);

        assertNull(mapper.toDtoList(null));
        assertEquals(1, mapper.toDtoList(establishments).size());
    }

    @Test
    void establishmentToFullEstablishmentDto() {
        assertNull(mapper.establishmentToFullEstablishmentDto(null));
    }
}