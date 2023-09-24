package com.team6.webproject.controller.main;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;
    @Test
    void getAllCustomers() {
        customerController.getAllCustomers(1, "", "", "", "");
        assertTrue(true);
    }

    @Test
    void findCustomerById() {
        customerController.findCustomerById(1);
        assertTrue(true);
    }

    @Test
    void saveNewCustomer() {
        AddCustomerDto customerDto = new AddCustomerDto();
        customerController.saveNewCustomer(customerDto);
        assertTrue(true);
    }

    @Test
    void deleteCustomer() {
        customerController.deleteCustomer(1);
        assertTrue(true);
    }

    @Test
    void updateCustomer() {
        UpdateCustomerDto customerDto = new UpdateCustomerDto();
        customerController.updateCustomer(customerDto);
        assertTrue(true);
    }
}