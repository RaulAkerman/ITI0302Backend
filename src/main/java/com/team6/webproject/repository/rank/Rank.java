package com.team6.webproject.repository.rank;

import com.team6.webproject.repository.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rankName;

    @OneToMany(mappedBy = "rank")
    private List<Customer> customers;
}