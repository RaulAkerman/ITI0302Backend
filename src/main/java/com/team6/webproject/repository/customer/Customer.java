package com.team6.webproject.repository.customer;
import com.team6.webproject.repository.rank.Rank;
import com.team6.webproject.repository.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fname;

    private String lastName;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private Rank rank;

    @OneToMany(mappedBy = "bookedCustomer")
    private List<Reservation> reservations;

    private Integer nrOfVisits;

    private String customerNationalId;

    private String favoriteEstablishment;
}
