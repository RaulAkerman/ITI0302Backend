package com.team6.webproject.repository.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomerCriteriaRepository {
    private final EntityManager entityManager;

    public List<Customer> findByFilter(int page, CustomerFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> customerQuery = cb.createQuery(Customer.class);
        Root<Customer> root = customerQuery.from(Customer.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (filter.getFname() != null) {
            predicatesList.add(cb.like(root.get("fname"), filter.getFname() + "%"));
        }
        if (filter.getLastName() != null) {
            predicatesList.add(cb.like(root.get("lastName"), filter.getLastName() + "%"));
        }
        customerQuery.select(root).where(predicatesList.toArray(new Predicate[0]));
        if ("ASC".equals(filter.getOrder())) {
            customerQuery.orderBy(cb.asc(root.get(filter.getOrderBy())));
        } else {
            customerQuery.orderBy(cb.desc(root.get(filter.getOrderBy())));
        }
        var entityManagedQuery = entityManager.createQuery(customerQuery);
        entityManagedQuery.setFirstResult(page * 5);
        entityManagedQuery.setMaxResults(5);
        return entityManagedQuery.getResultList();
    }

    public Long searchCount(CustomerFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> customerQuery = cb.createQuery(Long.class);
        Root<Customer> root = customerQuery.from(Customer.class);
        List<Predicate> predicatesList = new ArrayList<>();
        if (filter.getFname() != null) {
            predicatesList.add(cb.like(root.get("fname"), filter.getFname() + "%"));
        }
        if (filter.getLastName() != null) {
            predicatesList.add(cb.like(root.get("lname"), filter.getLastName() + "%"));
        }
        customerQuery.where(cb.and(predicatesList.toArray(new Predicate[0])));
        customerQuery.select(cb.count(root));
        return entityManager.createQuery(customerQuery).getSingleResult();
    }
}
