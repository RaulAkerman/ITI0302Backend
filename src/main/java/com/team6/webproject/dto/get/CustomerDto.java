package com.team6.webproject.dto.get;
import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String fname;
    private String lastName;
    private String rankName;
    private Integer nrOfVisits;
    private String customerNationalId;
}
