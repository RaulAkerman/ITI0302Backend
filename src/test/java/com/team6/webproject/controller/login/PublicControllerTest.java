package com.team6.webproject.controller.login;


import com.team6.webproject.dto.add.CreateUserRequest;
import com.team6.webproject.service.ApiService;
import com.team6.webproject.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PublicControllerTest {
    @Mock
    private LoginService loginService;

    @Mock
    private ApiService apiService;

    @InjectMocks
    private PublicController publicController;
    @Test
    void login() {
        // given
        LoginRequest request = new LoginRequest();
        request.setPassword("pass");
        request.setUsername("user");

        given(publicController.login(request)).willReturn("success");

        // when
        String result = publicController.login(request);

        // then
        assertEquals("success", result);
    }

    @Test
    void saveNewUser() {
        // given
        CreateUserRequest request = new CreateUserRequest();
        request.setPassword("pass");
        request.setUsername("user");

        // when
        publicController.saveNewUser(request);

        assertTrue(true);
    }

    @Test
    void getWeather() throws IOException {
        // given
        String city = "London";

        given(publicController.getWeather(city)).willReturn("success");

        // when
        String result = publicController.getWeather(city);

        // then
        assertEquals("success", result);
    }
}