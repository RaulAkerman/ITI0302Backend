package com.team6.webproject.controller.main;

import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.dto.update.UpdateReservationDto;
import com.team6.webproject.repository.reservation.ReservationFilter;
import com.team6.webproject.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    @PostMapping("/api/reservation/saveReservation/")
    public void postReservation(@RequestBody ReservationDto reservationDto) {
        log.info("Started postReservation service....");
        reservationService.saveReservation(reservationDto);
    }

    @PostMapping("/api/reservation/updateReservation/")
    public void updateReservation(@RequestBody UpdateReservationDto reservationDto) {
        log.info("Started updateReservation service....");
        reservationService.updateReservation(reservationDto);
    }

    @PostMapping("/api/reservation/deleteReservation/{id}/")
    public void deleteReservation(@PathVariable Integer id) {
        log.info("Started deleteReservation service....");
        reservationService.deleteReservation(id);
    }

    @GetMapping("/api/reservation/getAll")
    public List<GetReservationDto> getAllReservations(Integer page, String order, String orderBy, String date, String establishment) {
        log.info("Started getAllReservations service....");
        ReservationFilter filter = new ReservationFilter(date, order, orderBy, establishment);
        return reservationService.findall(page, filter);
    }

    @GetMapping("/api/reservation/getByStatus/{status}/")
    public List<GetReservationDto> getReservationsByStatus(@PathVariable("status") String status) {
        log.info("Started getReservationsByStatus service....");
        return reservationService.getByStatus(status);
    }

    @GetMapping("/api/reservation/getByEstablishment/{establishment}/")
    public List<GetReservationDto> getReservationsByEstablishment(@PathVariable("establishment") Integer establishment) {
        log.info("Started getReservationsByEstablishment service....");
        return reservationService.getByEstablishment(establishment);
    }
    //
    @PostMapping("/api/reservation/makeReservation/{nationalId}/{reservationId}/")
    public void makeReservation(@PathVariable("nationalId") String nationalId,
                                @PathVariable("reservationId") Integer reservationId) {
        log.info("Started makeReservation service....");
        reservationService.makeReservation(nationalId, reservationId);
    }

    @PostMapping("/api/reservation/fulfill/{reservationId}/")
    public void fulfillCustomerReservation(@PathVariable("reservationId") Integer reservationId) {
        log.info("Started fulfillCustomerReservation service....");
        reservationService.fulfillCustomerReservation(reservationId);
    }

    @GetMapping("/api/reservation/get/{nationalId}/")
    public List<GetReservationDto> getCustomerActiveReservations(@PathVariable("nationalId") String nationalId) {
        log.info("Started getCustomerActiveReservations service....");
        return reservationService.getCustomerActiveReservations(nationalId);
    }

    @GetMapping("/api/reservation/getHistory/{nationalId}/")
    public List<GetReservationDto> getCustomerReservationHistory(@PathVariable("nationalId") String nationalId) {
        log.info("Started getCustomerReservationHistory service....");
        return reservationService.getCustomerReservationHistory(nationalId);
    }

    @PostMapping("/api/reservation/cancel/{id}")
    public void cancelCustomerReservation(@PathVariable("id") Integer id) {
        log.info("Started cancelCustomerReservation service....");
        reservationService.cancelReservation(id);
    }
}
