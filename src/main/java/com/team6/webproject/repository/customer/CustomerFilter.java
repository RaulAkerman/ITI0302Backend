package com.team6.webproject.repository.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerFilter {
    String orderBy;
    String order;
    String fname;
    String lastName;
    Integer rankId;
    Integer nrOfVisits;
    String nationalId;

    public CustomerFilter(String orderBy, String order, String fname, String lastName) {
        this.order = order;
        this.orderBy = orderBy;
        this.fname = fname;
        this.lastName = lastName;
    }
}
