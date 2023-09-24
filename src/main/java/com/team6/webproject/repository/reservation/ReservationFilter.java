package com.team6.webproject.repository.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationFilter {

    String date;
    String order;
    String orderBy;
    String establishment;

    public ReservationFilter(String date, String order, String orderBy, String establishment) {
        this.date = date;
        this.orderBy = orderBy;
        this.order = order;
        this.establishment = establishment;
    }

    public String getOrderBy() {
        return orderBy;
    }
}
