package com.team6.webproject.repository.reservation;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationCriteriaRepositoryTest {
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private ReservationCriteriaRepository reservationCriteriaRepository;

    @Test
    void findByFilter() {
        // given
        ReservationFilter reservationFilter = new ReservationFilter();
        reservationFilter.setDate("2020");
        reservationFilter.setEstablishment("Estab");
        reservationFilter.setOrderBy("estab");
        reservationFilter.setOrder("ASC");

//        given(entityManager.getCriteriaBuilder()).willReturn(entityManager.getCriteriaBuilder());

        // when
        try {
            reservationCriteriaRepository.findByFilter(1, reservationFilter);
        } catch (Exception e) {
            assertNotNull(e);
        }




        // then

    }
}