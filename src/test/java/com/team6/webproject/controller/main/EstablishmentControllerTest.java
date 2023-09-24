package com.team6.webproject.controller.main;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import com.team6.webproject.dto.get.FullEstablishmentDto;
import com.team6.webproject.service.EstablishmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EstablishmentControllerTest {
    @Mock
    private EstablishmentService establishmentService;

    @InjectMocks
    private EstablishmentController establishmentController;

    @Test
    void addEstablishment() {
        AddEstablishmentDto establishmentDto = new AddEstablishmentDto();
        establishmentController.addEstablishment(establishmentDto);
        assertTrue(true);
    }

    @Test
    void deleteEstablishment() {
        establishmentController.deleteEstablishment(1);
        assertTrue(true);
    }

    @Test
    void updateEstablishment() {
        FullEstablishmentDto establishmentDto = new FullEstablishmentDto();
        establishmentController.updateEstablishment(establishmentDto);
        assertTrue(true);
    }

    @Test
    void getAllEstablishments() {
        establishmentController.getAllEstablishments(1, "", "", "");
        assertTrue(true);
    }
}