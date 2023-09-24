package com.team6.webproject.repository.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Repository
public class ReservationCriteriaRepository {
    private final EntityManager entityManager;

    public List<Reservation> findByFilter(int page, ReservationFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reservation> reservationQuery = cb.createQuery(Reservation.class);
        Root<Reservation> root = reservationQuery.from(Reservation.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (filter.getDate() != null) {
            predicatesList.add(cb.like(root.get("date"), filter.getDate() + "%"));
        }
        if (filter.getEstablishment() != null) {
            predicatesList.add(cb.like(root.get("establishment"), filter.getEstablishment() + "%"));
        }
        reservationQuery.select(root).where(predicatesList.toArray(new Predicate[0]));
        if ("ASC".equals(filter.getOrder())) {
            reservationQuery.orderBy(cb.asc(root.get(filter.getOrderBy())));
        } else {
            reservationQuery.orderBy(cb.desc(root.get(filter.getOrderBy())));
        }
        var entityManagedQuery = entityManager.createQuery(reservationQuery);
        entityManagedQuery.setFirstResult(page * 3);
        entityManagedQuery.setMaxResults(3);
        return entityManagedQuery.getResultList();
    }
}
