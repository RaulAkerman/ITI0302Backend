package com.team6.webproject.mapper;

import com.team6.webproject.dto.add.AddCustomerDto;
import com.team6.webproject.dto.update.UpdateCustomerDto;
import com.team6.webproject.repository.customer.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T17:04:48+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class CustomerDtoToCustomerMapperImpl implements CustomerDtoToCustomerMapper {

    @Override
    public Customer updateToCustomer(UpdateCustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFname( customerDto.getFname() );
        customer.setLastName( customerDto.getLastName() );
        customer.setCustomerNationalId( customerDto.getCustomerNationalId() );

        return customer;
    }

    @Override
    public Customer toCustomer(AddCustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFname( customerDto.getFname() );
        customer.setLastName( customerDto.getLastName() );
        customer.setNrOfVisits( customerDto.getNrOfVisits() );
        customer.setCustomerNationalId( customerDto.getCustomerNationalId() );

        return customer;
    }
}
