package com.team6.webproject.repository.establishment;

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
public class EstablishmentCriteriaRepository {

    private final EntityManager entityManager;

    public List<Establishment> findByFilter(int page, EstablishmentFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Establishment> reservationQuery = cb.createQuery(Establishment.class);
        Root<Establishment> root = reservationQuery.from(Establishment.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (filter.getName() != null) {
            predicatesList.add(cb.like(root.get("name"), filter.getName() + "%"));
        }
        reservationQuery.select(root).where(predicatesList.toArray(new Predicate[0]));
        if ("ASC".equals(filter.getOrder())) {
            reservationQuery.orderBy(cb.asc(root.get(filter.getOrderBy())));
        } else {
            reservationQuery.orderBy(cb.desc(root.get(filter.getOrderBy())));
        }
        var entityManagedQuery = entityManager.createQuery(reservationQuery);
        entityManagedQuery.setFirstResult(page * 5);
        entityManagedQuery.setMaxResults(5);
        return entityManagedQuery.getResultList();
    }
}
