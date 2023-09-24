package com.team6.webproject.service;

import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.dto.update.UpdateReservationDto;
import com.team6.webproject.exception.ApplicationException;
import com.team6.webproject.mapper.ReservationDtoToReservationMapper;
import com.team6.webproject.mapper.ReservationToDtoMapper;
import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.customer.CustomerRepository;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.establishment.EstablishmentsRepository;
import com.team6.webproject.repository.rank.RanksRepository;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationCriteriaRepository;
import com.team6.webproject.repository.reservation.ReservationFilter;
import com.team6.webproject.repository.reservation.ReservationsRepository;
import com.team6.webproject.repository.user.User;
import com.team6.webproject.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RequiredArgsConstructor
@Service
@Slf4j
public class ReservationService {
    private final RanksRepository ranksRepository;
    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EstablishmentsRepository establishmentsRepository;
    private final ReservationDtoToReservationMapper reservationDtoToReservationMapper;
    private final ReservationToDtoMapper reservationToDtoMapper;

    private final ReservationCriteriaRepository reservationCriteriaRepository;

    private final CustomerService customerService;

    private static final String RESERVED = "reserved";
    @Transactional
    public List<GetReservationDto> findall(Integer page, ReservationFilter filter) {

        List<Reservation> allReservations = reservationCriteriaRepository.findByFilter(page, filter);
        List<GetReservationDto> dtoreservations = reservationToDtoMapper.toDtoList(allReservations);
        for (int i = 0; i < dtoreservations.size(); i++) {
            dtoreservations.get(i).setEstablishment(allReservations.get(i).getEstablishment().getName());
            dtoreservations.get(i).setUsername(allReservations.get(i).getUser().getUsername());
        }
        log.info("Finished findallReservations service!");
        return dtoreservations;
    }

    @Transactional
    public void saveReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationDtoToReservationMapper.toReservation(reservationDto);
        List<String> dateSplit = List.of(reservationDto.getDate().split("-"));

        reservation.setEstablishment(establishmentsRepository.getReferenceById(reservationDto.getEstablishment()));
        reservation.setUser(userRepository.findByUsername(reservationDto.getUsername()));

        reservation.setYear(Integer.valueOf(dateSplit.get(0)));
        reservation.setMonth(Integer.valueOf(dateSplit.get(1)));
        reservation.setDay(Integer.valueOf(dateSplit.get(2)));

        reservationsRepository.save(reservation);
        log.info("Finished saveReservation service, created reservation for " + reservation.getEstablishment().getName());
    }

    @Transactional
    public void updateReservation(UpdateReservationDto updateReservationDto) {
        Reservation reservation = reservationsRepository.getReferenceById(updateReservationDto.getId());
        reservation.setDate(updateReservationDto.getDate());
        reservationsRepository.save(reservation);
        log.info("Finished saveReservation service, updated reservation for " + reservation.getEstablishment().getName());
    }

    @Transactional
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationsRepository.getReferenceById(id);
        if (reservation.getBookedCustomer() != null) {
            log.error("Cannot delete reserved reservation");
            throw new ApplicationException("Cannot delete error.");
        } else {
            reservationsRepository.deleteById(id);
            log.info("Finished deleteReservation service, deletedId = " + id);
        }
    }

    @Transactional
    public List<GetReservationDto> getByStatus(String status) {
        List<Reservation> allReservations = reservationsRepository.findAllByStatusIs(status);
        List<GetReservationDto> allReservationDtos = reservationToDtoMapper.toDtoList(allReservations);

        int n = 0;
        for (Reservation reservation: allReservations) {
            Customer customer = reservation.getBookedCustomer();
            Establishment establishment = reservation.getEstablishment();
            User user = reservation.getUser();
            allReservationDtos.get(n).setUsername(user.getUsername());
            allReservationDtos.get(n).setEstablishment(establishment.getName());
            if (customer != null) {
                String fullName = customer.getFname() + " " + customer.getLastName();
                allReservationDtos.get(n).setBookedCustomer(fullName);
            }
            n++;
        }

        log.info("Finished getByStatus service, found " + allReservationDtos.size() + " reservations with status = " + status);
        return allReservationDtos;
    }

    @Transactional
    public List<GetReservationDto> getByEstablishment(Integer establishment) {
        Establishment establishmentObject = establishmentsRepository.getReferenceById(establishment);
        List<Reservation> allReservations = reservationsRepository.findAllByEstablishmentIs(establishmentObject);
        List<GetReservationDto> allReservationDtos = reservationToDtoMapper.toDtoList(allReservations);

        int n = 0;
        for (Reservation reservation: allReservations) {
            Customer customer = reservation.getBookedCustomer();
            User user = reservation.getUser();
            allReservationDtos.get(n).setUsername(user.getUsername());
            allReservationDtos.get(n).setEstablishment(establishmentObject.getName());
            if (customer != null) {
                String fullName = customer.getFname() + " " + customer.getLastName();
                allReservationDtos.get(n).setBookedCustomer(fullName);
            }
            n++;
        }

        log.info("Finished getByEstablishment service, found " + allReservationDtos.size() + " reservations with establishment = " + establishmentObject.getName());
        return allReservationDtos;
    }


    @Transactional
    public void makeReservation(String nationalId, Integer reservationId) {
        Customer customer = customerRepository.findByCustomerNationalId(nationalId);
        Reservation reservation;
        reservation = reservationsRepository.getReferenceById(reservationId);
        if (reservation.getBookedCustomer() != null) {
            log.error("Reservation already taken!");
            throw new ApplicationException("Reservation taken error.");
        } else {
            reservation.setStatus(RESERVED);
            reservation.setBookedCustomer(customer);
            log.info("Reservation successfully made!");
            log.info("Finished makeReservation service.");
        }
    }

    @Transactional
    public void cancelReservation(Integer id) {
        Reservation reservation = reservationsRepository.getReferenceById(id);
        if (Objects.equals(reservation.getStatus(), RESERVED)) {
            reservation.setStatus("available");
            reservation.setBookedCustomer(null);
            log.info("Reservation with id = " + id + " successfully cancelled!");
            log.info("Finished cancelReservation service.");
        } else {
            log.error("No active reservation with id = " + id + "!");
            throw new ApplicationException("No active reservation with given id error.");
        }


    }

    @Transactional
    public void fulfillCustomerReservation(Integer reservationId) {
        if (reservationsRepository.existsById(reservationId)) {
            Optional<Reservation> reservation = reservationsRepository.findById(reservationId);
            if (reservation.isPresent()) {
                reservation.get().setStatus("fulfilled");
                Customer customer = reservation.get().getBookedCustomer();
                int previous = customer.getNrOfVisits();
                customer.setNrOfVisits(previous + 1);
                if (customer.getNrOfVisits() >= 3) {
                    customer.setRank(ranksRepository.getReferenceById(2));
                    log.info("Customer rank increased!");
                }
                log.info("Finished fulfillCustomerReservation service, fulfilled reservation = " + reservationId);
            }
        } else {
            log.error("No such reservation");
            throw new ApplicationException("No such reservation error.");
        }

    }

    public List<GetReservationDto> getCustomerReservationHistory(String nationalId) {
        Customer customer = customerRepository.findByCustomerNationalId(nationalId);
        List<Reservation> reservationList = reservationsRepository.findAllByBookedCustomer(customer);
        List<GetReservationDto> allReservationDtos = reservationToDtoMapper.toDtoList(reservationList);

        int n = 0;
        for (Reservation reservation: reservationList) {
            Establishment establishment = reservation.getEstablishment();
            User user = reservation.getUser();

            allReservationDtos.get(n).setUsername(user.getUsername());
            allReservationDtos.get(n).setEstablishment(establishment.getName());

            if (customer != null) {
                String fullName = customer.getFname() + " " + customer.getLastName();
                allReservationDtos.get(n).setBookedCustomer(fullName);
            }
            n++;
        }
        log.info("Finished getByEstablishment service, found " + allReservationDtos.size() + " reservations");

        return allReservationDtos;
    }

    public List<GetReservationDto> getCustomerActiveReservations(String nationalId) {
        Customer customer = customerRepository.findByCustomerNationalId(nationalId);
        List<Reservation> reservationList = reservationsRepository.findAllByBookedCustomer(customer);
        List<Reservation> activeReservations = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            if (Objects.equals(reservation.getStatus(), RESERVED)) {
                activeReservations.add(reservation);
            }
        }

        List<GetReservationDto> allReservationDtos = reservationToDtoMapper.toDtoList(activeReservations);

        int n = 0;
        for (Reservation reservation: activeReservations) {
            Establishment establishment = reservation.getEstablishment();
            User user = reservation.getUser();
            allReservationDtos.get(n).setUsername(user.getUsername());
            allReservationDtos.get(n).setEstablishment(establishment.getName());
            if (customer != null) {
                String fullName = customer.getFname() + " " + customer.getLastName();
                allReservationDtos.get(n).setBookedCustomer(fullName);
            }
            n++;
        }
        assert customer != null;
        customerService.addFavoriteEstablishment(customer.getId(), activeReservations.get(0).getEstablishment().getId());
        log.info("Finished getCustomerActiveReservations service, found " + allReservationDtos.size() + " reservations");
        return allReservationDtos;
    }
}
