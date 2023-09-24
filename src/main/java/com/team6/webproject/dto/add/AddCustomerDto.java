package com.team6.webproject.dto.add;

import lombok.Data;

@Data
public class AddCustomerDto {
    private String fname;
    private String lastName;
    private Integer rankId;
    private Integer nrOfVisits;
    private String customerNationalId;
}
