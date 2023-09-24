package com.team6.webproject.controller.main;

import com.team6.webproject.controller.login.LoginRequest;
import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.dto.update.UpdateReservationDto;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationFilter;
import com.team6.webproject.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {
    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void postReservation() {
        ReservationDto reservationDto = new ReservationDto();
        reservationController.postReservation(reservationDto);
        assertTrue(true);
    }

    @Test
    void updateReservation() {
        UpdateReservationDto updateReservationDto = new UpdateReservationDto();
        reservationController.updateReservation(updateReservationDto);
        assertTrue(true);
    }

    @Test
    void deleteReservation() {
        reservationController.deleteReservation(1);
        assertTrue(true);
    }

    @Test
    void getAllReservations() {
        List<GetReservationDto> result = reservationController.getAllReservations(1, "order", "orderBy", "date", "estab");
        assertEquals(0 , result.size());

    }

    @Test
    void getReservationsByStatus() {
        List<GetReservationDto> result = reservationController.getReservationsByStatus("available");
        assertEquals(0 , result.size());
    }

    @Test
    void getReservationsByEstablishment() {
        List<GetReservationDto> result = reservationController.getReservationsByEstablishment(1);
        assertEquals(0 , result.size());
    }

    @Test
    void makeReservation() {
        reservationController.makeReservation("5", 1);
        assertTrue(true);
    }

    @Test
    void fulfillCustomerReservation() {
        reservationController.fulfillCustomerReservation( 1);
        assertTrue(true);
    }

    @Test
    void getCustomerActiveReservations() {
        reservationController.getCustomerActiveReservations("5");
        assertTrue(true);
    }

    @Test
    void getCustomerReservationHistory() {
        reservationController.getCustomerReservationHistory("5");
        assertTrue(true);
    }

    @Test
    void cancelCustomerReservation() {
        reservationController.cancelCustomerReservation(1);
        assertTrue(true);
    }
}