package com.team6.webproject.dto.get;

import lombok.Data;

@Data
public class GetReservationDto {
    private Integer id;
    private String establishment;
    private String date;
    private String status;
    private String bookedCustomer;
    private String username;
}
