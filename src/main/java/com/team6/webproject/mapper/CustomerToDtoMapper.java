package com.team6.webproject.mapper;

import com.team6.webproject.dto.get.CustomerDto;
import com.team6.webproject.repository.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerToDtoMapper {

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customer);
}
