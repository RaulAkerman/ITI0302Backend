package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.repository.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerDtoToCustomerMapper {

    Customer updateToCustomer(UpdateCustomerDto customerDto);
    Customer toCustomer(AddCustomerDto customerDto);
}
