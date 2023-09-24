package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstablishmentDtoToEstablishmentMapperImplTest {

    private final EstablishmentDtoToEstablishmentMapperImpl mapper = new EstablishmentDtoToEstablishmentMapperImpl();

    @Test
    void toEstablishment() {
        AddEstablishmentDto establishmentDto = new AddEstablishmentDto();
        establishmentDto.setName("estab");

        assertNull(mapper.toEstablishment(null));
        assertEquals("estab", mapper.toEstablishment(establishmentDto).getName());
    }
}