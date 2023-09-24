package com.team6.webproject.repository.reservation;

import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.establishment.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByStatusIs(String status);

    List<Reservation> findAllByBookedCustomer(Customer customer);
    List<Reservation> findAllByEstablishmentIs(Establishment establishment);


}
