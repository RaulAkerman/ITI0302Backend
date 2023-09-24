package com.team6.webproject.service;


import com.team6.webproject.dto.add.ReservationDto;
import com.team6.webproject.dto.get.GetReservationDto;
import com.team6.webproject.dto.update.UpdateReservationDto;
import com.team6.webproject.exception.ApplicationException;
import com.team6.webproject.mapper.*;
import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.customer.CustomerRepository;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.establishment.EstablishmentsRepository;
import com.team6.webproject.repository.rank.Rank;
import com.team6.webproject.repository.rank.RanksRepository;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationCriteriaRepository;
import com.team6.webproject.repository.reservation.ReservationFilter;
import com.team6.webproject.repository.reservation.ReservationsRepository;
import com.team6.webproject.repository.user.User;
import com.team6.webproject.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ReservationsRepository reservationsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RanksRepository ranksRepository;
    @Mock
    private EstablishmentsRepository establishmentsRepository;
    @Mock
    private ReservationCriteriaRepository criteriaRepository;

    @Spy
    private ReservationDtoToReservationMapper dtoToReservationMapper = new ReservationDtoToReservationMapperImpl();
    @Spy
    private ReservationToDtoMapper reservationToDtoMapper = new ReservationToDtoMapperImpl();

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void findall() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);

        ReservationFilter reservationFilter = new ReservationFilter("date", "ASC", "date", "TEST");

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);

        given(criteriaRepository.findByFilter(1, reservationFilter)).willReturn(reservations);

        // when
        List<GetReservationDto> result = reservationService.findall(1, reservationFilter);

        // then
        then(criteriaRepository).should().findByFilter(1, reservationFilter);
        then(reservationToDtoMapper).should().toDtoList(reservations);

        assertNotNull(result);
    }

    @Test
    void saveReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023-02-01");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);


        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDate("2023-02-01");
        reservationDto.setEstablishment(1);
        reservationDto.setStatus("available");
        reservationDto.setUsername("TEST");

        given(establishmentsRepository.getReferenceById(1)).willReturn(establishment);

        // when
        reservationService.saveReservation(reservationDto);

        // then
        then(dtoToReservationMapper).should().toReservation(reservationDto);
        then(establishmentsRepository).should().getReferenceById(reservationDto.getEstablishment());
    }

    @Test
    void updateReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023-02-01");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);


        UpdateReservationDto reservationDto = new UpdateReservationDto();
        reservationDto.setDate("2023-02-02");
        reservationDto.setId(1);

        given(reservationsRepository.getReferenceById(1)).willReturn(reservation1);

        // when
        reservationService.updateReservation(reservationDto);

        // then
        then(reservationsRepository).should().getReferenceById(1);

        assertEquals("2023-02-02", reservation1.getDate());
    }

    @Test
    void deleteReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023-02-01");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setId(1);

        given(reservationsRepository.getReferenceById(1)).willReturn(reservation1);

        // when
        reservationService.deleteReservation(1);

        // then
        then(reservationsRepository).should().getReferenceById(1);

        // Reserved reservation's exception.
        // given
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Reservation reservation2 = new Reservation();
        reservation2.setStatus("available");
        reservation2.setDate("2023-02-01");
        reservation2.setEstablishment(establishment);
        reservation2.setUser(user);
        reservation2.setId(2);
        reservation2.setBookedCustomer(customer);

        given(reservationsRepository.getReferenceById(2)).willReturn(reservation2);

        // when
        try {
            reservationService.deleteReservation(2);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }
    }

    @Test
    void getByStatus() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setBookedCustomer(customer);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);

        given(reservationsRepository.findAllByStatusIs("available")).willReturn(reservations);

        // when
        List<GetReservationDto> result = reservationService.getByStatus("available");

        // then
        then(reservationsRepository).should().findAllByStatusIs("available");
        then(reservationToDtoMapper).should().toDtoList(reservations);

        assertEquals(1, result.size());

    }

    @Test
    void getByEstablishment() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setBookedCustomer(customer);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);

        given(establishmentsRepository.getReferenceById(1)).willReturn(establishment);
        given(reservationsRepository.findAllByEstablishmentIs(establishment)).willReturn(reservations);

        // when
        List<GetReservationDto> result = reservationService.getByEstablishment(1);

        // then
        then(establishmentsRepository).should().getReferenceById(1);
        then(reservationsRepository).should().findAllByEstablishmentIs(establishment);
        then(reservationToDtoMapper).should().toDtoList(reservations);

        assertEquals(1, result.size());
    }

    @Test
    void makeReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setId(1);

        given(customerRepository.findByCustomerNationalId("55555")).willReturn(customer);
        given(reservationsRepository.getReferenceById(1)).willReturn(reservation1);

        // when
        reservationService.makeReservation("55555", 1);

        // then
        then(customerRepository).should().findByCustomerNationalId("55555");
        then(reservationsRepository).should().getReferenceById(1);

        assertEquals(customer, reservation1.getBookedCustomer());

        // Reservation taken.
        // when
        try {
            reservationService.makeReservation("55555", 1);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }
    }

    @Test
    void cancelReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("reserved");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setId(1);

        given(reservationsRepository.getReferenceById(1)).willReturn(reservation1);

        // when
        reservationService.cancelReservation(1);

        // then
        then(reservationsRepository).should().getReferenceById(1);

        assertNull(reservation1.getBookedCustomer());

        // Reservation taken.

        // given
        Reservation reservation2 = new Reservation();
        reservation2.setStatus("available");
        reservation2.setId(2);
        given(reservationsRepository.getReferenceById(2)).willReturn(reservation2);

        // when
        try {
            reservationService.cancelReservation(2);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }
    }

    @Test
    void fulfillCustomerReservation() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        Rank rank = new Rank();
        rank.setRankName("VIP");
        rank.setId(2);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(3);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);
        reservation1.setId(1);
        reservation1.setBookedCustomer(customer);

        given(reservationsRepository.existsById(1)).willReturn(true);
        given(reservationsRepository.findById(1)).willReturn(Optional.of(reservation1));
        given(ranksRepository.getReferenceById(2)).willReturn(rank);

        // when
        reservationService.fulfillCustomerReservation(1);

        // then
        then(reservationsRepository).should().findById(1);

        assertEquals(rank , customer.getRank());

        // Reservation taken.

        // given
        given(reservationsRepository.existsById(3)).willReturn(false);

        // when
        try {
            reservationService.fulfillCustomerReservation(3);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }
    }

    @Test
    void getCustomerReservationHistory() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(3);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("available");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);


        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);

        List<GetReservationDto> reservationDtos = new ArrayList<>();
        GetReservationDto reservationDto = new GetReservationDto();
        reservationDtos.add(reservationDto);

        given(customerRepository.findByCustomerNationalId("55555")).willReturn(customer);
        given(reservationsRepository.findAllByBookedCustomer(customer)).willReturn(reservations);
//        given(reservationToDtoMapper.toDtoList(reservations)).willReturn(reservationDtos);

        // when
        reservationService.getCustomerReservationHistory("55555");

        // then
        then(customerRepository).should().findByCustomerNationalId("55555");
        then(reservationsRepository).should().findAllByBookedCustomer(customer);
        then(reservationToDtoMapper).should().toDtoList(reservations);

        }

    @Test
    void getCustomerActiveReservations() {
        // given
        Establishment establishment = new Establishment();
        establishment.setName("TEST");
        establishment.setId(1);

        User user = new User();
        user.setUsername("employee");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(3);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("reserved");
        reservation1.setDate("2023");
        reservation1.setEstablishment(establishment);
        reservation1.setUser(user);


        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);


        given(customerRepository.findByCustomerNationalId("55555")).willReturn(customer);
        given(reservationsRepository.findAllByBookedCustomer(customer)).willReturn(reservations);

        // when
        reservationService.getCustomerActiveReservations("55555");

        // then
        then(customerRepository).should().findByCustomerNationalId("55555");
        then(reservationToDtoMapper).should().toDtoList(reservations);
    }
}
