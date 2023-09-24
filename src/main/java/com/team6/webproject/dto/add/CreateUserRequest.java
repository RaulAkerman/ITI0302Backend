package com.team6.webproject.dto.add;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
}
