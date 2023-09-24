package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.get.CustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.repository.customer.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDtoToCustomerMapperImplTest {

    private final CustomerDtoToCustomerMapperImpl mapper = new CustomerDtoToCustomerMapperImpl();

    @Test
    void updateToCustomer() {

        UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto();
        updateCustomerDto.setFname("John");

        assertNull(mapper.updateToCustomer(null));
        assertEquals("John", mapper.updateToCustomer(updateCustomerDto).getFname());
    }

    @Test
    void toCustomer() {
        AddCustomerDto addCustomerDto = new AddCustomerDto();
        addCustomerDto.setFname("John");

        assertNull(mapper.toCustomer(null));
        assertEquals("John", mapper.toCustomer(addCustomerDto).getFname());

    }
}