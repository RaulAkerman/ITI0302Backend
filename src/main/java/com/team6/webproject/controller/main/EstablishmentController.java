package com.team6.webproject.controller.main;

import com.team6.webproject.dto.add.AddEstablishmentDto;
import com.team6.webproject.dto.get.FullEstablishmentDto;
import com.team6.webproject.repository.establishment.EstablishmentFilter;
import com.team6.webproject.service.EstablishmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @PostMapping("/api/establishment/add")
    public void addEstablishment(@RequestBody AddEstablishmentDto establishmentDto) {
        log.info("Started addEstablishment service....");
        establishmentService.addEstablishment(establishmentDto);

    }

    @PostMapping("/api/establishment/delete/{id}")
    public void deleteEstablishment(@PathVariable("id") Integer id) {
        log.info("Started deleteEstablishment service....");
        establishmentService.deleteEstablishment(id);
    }

    @PostMapping("/api/establishment/update/")
    public void updateEstablishment(@RequestBody FullEstablishmentDto establishmentDto) {
        log.info("Started updateEstablishment service....");
        establishmentService.updateEstablishment(establishmentDto);
    }

    @GetMapping("/api/establishment/getAll")
    public List<FullEstablishmentDto> getAllEstablishments(Integer page, String name , String order, String orderBy) {
        log.info("Started getAllEstablishments service....");
        EstablishmentFilter filter = new EstablishmentFilter(name, order, orderBy);
        return establishmentService.findAll(page, filter);

    }
}
