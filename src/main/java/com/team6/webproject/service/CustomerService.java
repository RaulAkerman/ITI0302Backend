package com.team6.webproject.service;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.get.CustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.mapper.CustomerDtoToCustomerMapper;
import com.team6.webproject.mapper.CustomerToDtoMapper;
import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.customer.CustomerCriteriaRepository;
import com.team6.webproject.repository.customer.CustomerFilter;
import com.team6.webproject.repository.customer.CustomerRepository;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.establishment.EstablishmentsRepository;
import com.team6.webproject.repository.rank.RanksRepository;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationsRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ReservationsRepository reservationsRepository;
    private final RanksRepository ranksRepository;
    private final CustomerCriteriaRepository customerCriteriaRepository;

    private final CustomerToDtoMapper customerToDtoMapper;
    private final CustomerDtoToCustomerMapper customerDtoToCustomerMapper;

    private final EstablishmentsRepository establishmentRepository;

    @Transactional
    public List<CustomerDto> findAll(int page, CustomerFilter filter) {
        List<Customer> allCustomers = customerCriteriaRepository.findByFilter(page, filter);
        List<CustomerDto> allCustomerDtos = customerToDtoMapper.toDtoList(allCustomers);
        int i = 0;
        for (Customer customer : allCustomers) {
            String rank = ranksRepository.getReferenceById(customer.getRank().getId()).getRankName();
            allCustomerDtos.get(i).setRankName(rank);
            i++;
        }

        log.info("Finished getAllCustomers service.");
        return allCustomerDtos;
    }

    @Transactional
    public CustomerDto findCustomerById(Integer id) {
        if (customerRepository.existsById(id)) {
            Customer customer = customerRepository.getReferenceById(id);
            CustomerDto customerDto = customerToDtoMapper.toDto(customer);
            customerDto.setRankName(ranksRepository.getReferenceById(customer.getRank().getId()).getRankName());
            log.info("Finished successfully service findCustomerById = " + id + ", customer found!");
            return customerDto;
        } else {
            log.info("Finished successfully service findCustomerById = " + id + "!, no customer found!");
            return null;
        }
    }

    @Transactional
    public void saveCustomer(AddCustomerDto newCustomer) {
        Customer customer = customerDtoToCustomerMapper.toCustomer(newCustomer);
        customer.setRank(ranksRepository.getReferenceById(newCustomer.getRankId()));

        customerRepository.save(customer);
        log.info("Finished successfully service saveNewUser, "
                + customer.getFname() + " "
                + customer.getLastName() + " added to list of customers!");
    }

    @Transactional
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.getReferenceById(id);
        List<Reservation> reservationList = reservationsRepository.findAllByBookedCustomer(customer);

        if (!reservationList.isEmpty()) {
            for (Reservation reservation : reservationList) {
                if (Objects.equals(reservation.getStatus(), "reserved")) {
                    reservation.setStatus("available");
                    reservation.setBookedCustomer(null);
                } else if (Objects.equals(reservation.getStatus(), "fulfilled")) {
                    reservationsRepository.delete(reservation);
                }
            }
        }
        customerRepository.delete(customer);
        log.info("CustomerID = " + id + " and all their previous reservation history deleted.");
        log.info("Finished deleteCustomer service.");
    }

    public void addFavoriteEstablishment(Integer customerId, Integer establishmentId) {
        Customer customer = customerRepository.getReferenceById(customerId);
        Establishment establishment = establishmentRepository.getReferenceById(establishmentId);
        customer.setFavoriteEstablishment(establishment.getName());
        customerRepository.save(customer);
        log.info("EstablishmentID = " + establishmentId + " added to customerID = " + customerId + " favorites.");
        log.info("Finished addFavoriteEstablishment service.");
    }

    @Transactional
    public void updateCustomer(UpdateCustomerDto updateCustomerDto) {
        Customer updatedCustomer = customerRepository.findByCustomerNationalId(updateCustomerDto.getCustomerNationalId());
        updatedCustomer.setFname(updateCustomerDto.getFname());
        updatedCustomer.setLastName(updateCustomerDto.getLastName());
        updatedCustomer.setRank(ranksRepository.getReferenceById(updateCustomerDto.getRankId()));
        log.info("Updated information for " + updatedCustomer.getFname() + " " + updatedCustomer.getLastName() + "!");
        log.info("Finished updateCustomerService.");
        customerRepository.save(updatedCustomer);
    }

}
