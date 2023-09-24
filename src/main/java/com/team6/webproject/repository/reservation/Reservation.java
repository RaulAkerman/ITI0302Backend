package com.team6.webproject.repository.reservation;

import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String status;
    private Integer day;
    private Integer month;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "establishment")
    private Establishment establishment;


    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "booked_customer")
    private Customer bookedCustomer;
}
