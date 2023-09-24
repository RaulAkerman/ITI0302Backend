package com.team6.webproject.repository.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFilterTest {

    @Test
    void get() {

        CustomerFilter customerFilter = new CustomerFilter();
        customerFilter.setOrder("order");
        customerFilter.setLastName("last");
        customerFilter.setFname("first");
        customerFilter.setNrOfVisits(0);
        customerFilter.setRankId(1);
        customerFilter.setNationalId("5");
        customerFilter.setOrderBy("orderby");

        assertEquals("order", customerFilter.getOrder());
        assertEquals("last", customerFilter.getLastName());
        assertEquals("first", customerFilter.getFname());
        assertEquals(0, customerFilter.getNrOfVisits());
        assertEquals(1, customerFilter.getRankId());
        assertEquals("5", customerFilter.getNationalId());
        assertEquals("orderby", customerFilter.getOrderBy());

    }

}