package com.team6.webproject.dto.add;

import lombok.Data;

@Data
public class ReservationDto {
    private Integer establishment;
    private String date;
    private String status;
    private String username;
}
