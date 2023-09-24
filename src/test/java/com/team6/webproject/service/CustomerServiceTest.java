package com.team6.webproject.service;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.get.CustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.mapper.CustomerDtoToCustomerMapper;
import com.team6.webproject.mapper.CustomerDtoToCustomerMapperImpl;
import com.team6.webproject.mapper.CustomerToDtoMapper;
import com.team6.webproject.mapper.CustomerToDtoMapperImpl;
import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.customer.CustomerCriteriaRepository;
import com.team6.webproject.repository.customer.CustomerFilter;
import com.team6.webproject.repository.customer.CustomerRepository;
import com.team6.webproject.repository.rank.Rank;
import com.team6.webproject.repository.rank.RanksRepository;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ReservationsRepository reservationsRepository;
    @Mock
    private CustomerCriteriaRepository customerCriteriaRepository;
    @Mock
    private RanksRepository ranksRepository;

    @Spy
    private CustomerToDtoMapper customerToDtoMapper = new CustomerToDtoMapperImpl();
    @Spy
    private CustomerDtoToCustomerMapper customerDtoToCustomerMapper = new CustomerDtoToCustomerMapperImpl();

    @InjectMocks
    private CustomerService customerService;

    @Test
    void findAll() {
        // Customer exists.

        // given
        Rank rank = new Rank();
        rank.setRankName("NEUTRAL");
        ranksRepository.save(rank);
        rank.setId(1);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);
        customer.setRank(rank);

        List<Customer> list = new ArrayList<>();
        list.add(customer);

        CustomerFilter customerFilter = new CustomerFilter("Fname", "ASC", "John", "Doe");

        given(ranksRepository.getReferenceById(1)).willReturn(rank);
        given(customerCriteriaRepository.findByFilter(1, customerFilter)).willReturn(list);

        // when
        List<CustomerDto> result = customerService.findAll(1, customerFilter);

        // then
        then(customerCriteriaRepository).should().findByFilter(1, customerFilter);
        then(customerToDtoMapper).should().toDtoList(list);
        then(ranksRepository).should().getReferenceById(1);

        List<String> expected = new ArrayList<>();
        expected.add("CustomerDto(id=1, Fname=John, lastName=Doe, rankName=NEUTRAL, nrOfVisits=0, customerNationalId=55555)");
    }

    @Test
    void findCustomerById() {
        // Customer exists.

        // given
        Rank rank = new Rank();
        rank.setRankName("NEUTRAL");
        ranksRepository.save(rank);
        rank.setId(1);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);
        customer.setRank(rank);

        given(customerRepository.getReferenceById(1)).willReturn(customer);
        given(ranksRepository.getReferenceById(1)).willReturn(rank);
        given(customerRepository.existsById(1)).willReturn(true);

        // when
        CustomerDto result = customerService.findCustomerById(1);

        // then
        then(customerRepository).should().getReferenceById(1);
        then(customerToDtoMapper).should().toDto(customer);

        CustomerDto dto = new CustomerDto();

        dto.setId(1);
        dto.setFname("John");
        dto.setLastName("Doe");
        dto.setRankName("NEUTRAL");
        dto.setNrOfVisits(0);
        dto.setCustomerNationalId("55555");

        assertEquals(dto, result);

        // Customer doesn't exist.

        // given
        given(customerRepository.existsById(2)).willReturn(false);

        // when
        CustomerDto result2 = customerService.findCustomerById(2);

        // then

        assertNull(result2);
    }

    @Test
    void saveCustomer() {
        // Customer exists.

        // given
        Rank rank = new Rank();
        rank.setRankName("NEUTRAL");
        ranksRepository.save(rank);
        rank.setId(1);

        AddCustomerDto customer = new AddCustomerDto();
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);

        Customer customerObject = new Customer();
        customerObject.setId(1);
        customerObject.setFname("John");
        customerObject.setLastName("Doe");
        customerObject.setCustomerNationalId("55555");
        customerObject.setNrOfVisits(0);
        customerObject.setRank(rank);

        given(ranksRepository.getReferenceById(null)).willReturn(rank);

        // when
        customerService.saveCustomer(customer);

        // then
        then(customerDtoToCustomerMapper).should().toCustomer(customer);
        then(ranksRepository).should().getReferenceById(null);
    }

    @Test
    void deleteCustomer() {
        // Customer exists.

        // given
        Rank rank = new Rank();
        rank.setRankName("NEUTRAL");
        ranksRepository.save(rank);
        rank.setId(1);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setNrOfVisits(0);
        customer.setRank(rank);

        Reservation reservation1 = new Reservation();
        reservation1.setStatus("reserved");
        reservation1.setBookedCustomer(customer);

        Reservation reservation2 = new Reservation();
        reservation2.setStatus("fulfilled");


        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation1);
        reservationList.add(reservation2);

        given(customerRepository.getReferenceById(1)).willReturn(customer);
        given(reservationsRepository.findAllByBookedCustomer(customer)).willReturn(reservationList);

        // when
        customerService.deleteCustomer(1);

        // then
        then(customerRepository).should().getReferenceById(1);
        then(reservationsRepository).should().findAllByBookedCustomer(customer);
        then(reservationsRepository).should().delete(reservation2);
        then(customerRepository).should().delete(customer);

        assertEquals("available", reservation1.getStatus());
    }

    @Test
    void updateCustomer() {
        // Customer exists.

        // given
        Rank rank = new Rank();
        rank.setRankName("NEUTRAL");
        rank.setId(1);

        Rank rank2 = new Rank();
        rank.setRankName("VIP");
        rank.setId(1);

        Customer customerObject = new Customer();
        customerObject.setId(1);
        customerObject.setFname("Change");
        customerObject.setLastName("This");
        customerObject.setCustomerNationalId("55555");
        customerObject.setNrOfVisits(0);
        customerObject.setRank(rank);

        UpdateCustomerDto customer = new UpdateCustomerDto();
        customer.setFname("John");
        customer.setLastName("Doe");
        customer.setCustomerNationalId("55555");
        customer.setRankId(2);

        given(customerRepository.findByCustomerNationalId("55555")).willReturn(customerObject);
        given(ranksRepository.getReferenceById(2)).willReturn(rank2);

        // when
        customerService.updateCustomer(customer);

        // then
        then(customerRepository).should().findByCustomerNationalId("55555");
        then(ranksRepository).should().getReferenceById(2);
        then(customerRepository).should().save(customerObject);

        assertEquals("John", customerObject.getFname());
        assertEquals("Doe", customerObject.getLastName());
    }
}