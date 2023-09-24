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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class EstablishmentService {

    private final EstablishmentDtoToEstablishmentMapper establishmentDtoToEstablishmentMapper;
    private final EstablishmentsRepository establishmentsRepository;
    private final ReservationsRepository reservationsRepository;
    private final EstablishmentToFullDtoMapper establishmentToFullDtoMapper;

    private final EstablishmentCriteriaRepository establishmentCriteriaRepository;


    @Transactional
    public List<FullEstablishmentDto> findAll(Integer page, EstablishmentFilter filter) {
        log.info("Started findAll service....");
        List<Establishment> establishments = establishmentCriteriaRepository.findByFilter(page, filter);
        return establishmentToFullDtoMapper.toDtoList(establishments);
    }


    @Transactional
    public void addEstablishment(AddEstablishmentDto establishmentDto) {
        Establishment establishment = establishmentDtoToEstablishmentMapper.toEstablishment(establishmentDto);
        establishmentsRepository.save(establishment);
    }

    @Transactional
    public void deleteEstablishment(Integer id) {
        Establishment establishment = establishmentsRepository.getReferenceById(id);
        List<Reservation> reservationList = reservationsRepository.findAllByEstablishmentIs(establishment);
        if (!reservationList.isEmpty()) {
            for (Reservation reservation : reservationList) {
                if (Objects.equals(reservation.getStatus(), "reserved")) {
                    log.error("Cannot delete, establishment has active reservations.");
                    throw new ApplicationException("Cannot delete, active reservations error");
                }
            }
        }
            establishmentsRepository.deleteById(id);
            log.info("Finished deleteEstablishment service, deleted = " + establishment.getName());

    }

    @Transactional
    public void updateEstablishment(FullEstablishmentDto establishmentDto) {
        Establishment establishment = establishmentsRepository.getReferenceById(establishmentDto.getId());
        establishment.setName(establishmentDto.getName());
        establishmentsRepository.save(establishment);
        log.info("Finished updateEstablishment service.");
    }
}
