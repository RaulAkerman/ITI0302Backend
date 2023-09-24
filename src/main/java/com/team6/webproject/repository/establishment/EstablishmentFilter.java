package com.team6.webproject.repository.establishment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter@Setter
public class EstablishmentFilter {
    String name;

    String order;

    String orderBy;

    public EstablishmentFilter(String name, String order, String orderBy) {
        this.name = name;
        this.order = order;
        this.orderBy = orderBy;
    }
}
