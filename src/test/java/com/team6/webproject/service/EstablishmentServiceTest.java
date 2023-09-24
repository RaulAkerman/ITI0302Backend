package com.team6.webproject.service;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import com.team6.webproject.dto.get.FullEstablishmentDto;
import com.team6.webproject.exception.ApplicationException;
import com.team6.webproject.mapper.EstablishmentDtoToEstablishmentMapper;
import com.team6.webproject.mapper.EstablishmentToFullDtoMapper;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.establishment.EstablishmentCriteriaRepository;
import com.team6.webproject.repository.establishment.EstablishmentFilter;
import com.team6.webproject.repository.establishment.EstablishmentsRepository;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.reservation.ReservationsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith({MockitoExtension.class})
class EstablishmentServiceTest {
    @Spy
    private EstablishmentDtoToEstablishmentMapper establishmentDtoToEstablishmentMapper;
    @Spy
    private EstablishmentToFullDtoMapper establishmentToFullDtoMapper;
    @Mock
    private EstablishmentsRepository establishmentsRepository;
    @Mock
    private ReservationsRepository reservationsRepository;

    @Mock
    private EstablishmentCriteriaRepository establishmentCriteriaRepository;

    @InjectMocks
    private EstablishmentService establishmentService;


    @Test
    void addEstablishment() {
        //given
        AddEstablishmentDto establishment = new AddEstablishmentDto();
        establishment.setName("test");

        Establishment establishment1 = new Establishment();
        establishment1.setName("test");

        //when
        establishmentService.addEstablishment(establishment);
        //then
        then(establishmentDtoToEstablishmentMapper).should().toEstablishment(establishment);

    }

    @Test
    void deleteEstablishment() {
        //given
        Integer id = 1;
        Establishment establishment = new Establishment();
        establishment.setId(id);
        given(establishmentsRepository.getReferenceById(id)).willReturn(establishment);
        //when
        establishmentService.deleteEstablishment(id);
        //then
        then(establishmentsRepository).should().getReferenceById(id);
        then(reservationsRepository).should().findAllByEstablishmentIs(establishment);

    }

    @Test
    void updateEstablishment() {
        //given
        Integer id = 1;

        List<Establishment> establishments = new ArrayList<>();
        establishments.add(new Establishment());

        List<FullEstablishmentDto> establishmentDtos = new ArrayList<>();

        FullEstablishmentDto establishment = new FullEstablishmentDto();
        establishment.setName("test");
        establishment.setId(id);

        establishmentDtos.add(establishment);

        Establishment establishment1 = new Establishment();
        establishment1.setName("test");

        EstablishmentFilter filter = new EstablishmentFilter();

        given(establishmentCriteriaRepository.findByFilter(1, filter)).willReturn(establishments);
        given(establishmentToFullDtoMapper.toDtoList(establishments)).willReturn(establishmentDtos);
        //when
        establishmentService.findAll(1, filter);
        //then
        then(establishmentCriteriaRepository).should().findByFilter(1, filter);
    }

    @Test
    void findAllTest() {
        //given
        List<Establishment> establishmentDtos = new ArrayList<>();

        int page = 1;
        given(establishmentCriteriaRepository.findByFilter(page, null)).willReturn(establishmentDtos);
        //when
        establishmentService.findAll(page, null);
        //then
        then(establishmentCriteriaRepository).should().findByFilter(page, null);

    }

    @Test
    void deletionOfReservedEstablishment() {
        //given
        Integer id = 1;
        Establishment establishment = new Establishment();
        Reservation reservation = new Reservation();
        reservation.setEstablishment(establishment);
        reservation.setStatus("reserved");
        establishment.setId(id);
        given(establishmentsRepository.getReferenceById(id)).willReturn(establishment);
        given(reservationsRepository.findAllByEstablishmentIs(establishment)).willReturn(List.of(reservation));
        //when
        try {
            establishmentService.deleteEstablishment(id);
        } catch (Exception e) {
            //then
            assertEquals(ApplicationException.class, e.getClass());
        }
        //then
        then(establishmentsRepository).should().getReferenceById(id);
        then(reservationsRepository).should().findAllByEstablishmentIs(establishment);

    }
}