package com.team6.webproject.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerToDtoMapperImplTest {

    private final CustomerToDtoMapperImpl customerToDtoMapper = new CustomerToDtoMapperImpl();

    @Test
    void toDto() {
        assertNull(customerToDtoMapper.toDto(null));
    }

    @Test
    void toDtoList() {
        assertNull(customerToDtoMapper.toDtoList(null));
    }
}