package com.team6.webproject.controller.login;

import com.team6.webproject.dto.add.CreateUserRequest;
import com.team6.webproject.service.LoginService;
import com.team6.webproject.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PublicController {

    private final LoginService loginService;
    private final ApiService apiService;

    @PostMapping("/api/public/login")
    public String login(@RequestBody LoginRequest request) {
        log.info("Started login service....");
        return loginService.login(request);
    }

    @PostMapping("/api/public/create/user")
    public void saveNewUser(@RequestBody CreateUserRequest request) {
        log.info("Started saveNewUser service....");
        loginService.createUser(request);
    }

    @GetMapping("/api/public/weather/{city}/")
    public String getWeather(@PathVariable("city") String city) throws IOException {
        log.info("Started getWeather service....");
        return apiService.getWeather(city);
    }
}
