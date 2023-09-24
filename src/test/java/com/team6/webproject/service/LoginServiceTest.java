package com.team6.webproject.service;

import com.team6.webproject.controller.login.LoginRequest;
import com.team6.webproject.dto.add.CreateUserRequest;
import com.team6.webproject.exception.ApplicationException;
import com.team6.webproject.mapper.UserMapper;
import com.team6.webproject.repository.user.User;
import com.team6.webproject.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginService loginService;

    @Test
    void createUser() {
        // given
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("John");
        createUserRequest.setPassword("Doe");

        User user = new User();
        user.setUsername("John");
        user.setPassword("Doe");

        given(userMapper.toUser(createUserRequest)).willReturn(user);

        // when
        loginService.createUser(createUserRequest);

        // then
        then(userMapper).should().toUser(createUserRequest);
        then(passwordEncoder).should().encode(createUserRequest.getPassword());
        then(userRepository).should().save(user);

        // Username taken

        // given
        CreateUserRequest falseCreateUserRequest = new CreateUserRequest();
        falseCreateUserRequest.setUsername("John");
        falseCreateUserRequest.setPassword("Doe");

        given(userRepository.findByUsername("John")).willReturn(user);

        // when
        try {
            loginService.createUser(falseCreateUserRequest);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }


        // then

    }

    @Test
    void login() {
        // given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("John");
        loginRequest.setPassword("Doe");

        User user = new User();
        user.setUsername("John");
        user.setPassword("Doe");

        given(userRepository.findByUsername("John")).willReturn(user);
        given(passwordEncoder.matches("Doe", "Doe")).willReturn(true);

        // when
        String result = "";
        result = loginService.login(loginRequest);

        // then
        then(userRepository).should().findByUsername("John");
        then(passwordEncoder).should().matches("Doe","Doe");
        assertNotEquals("", result);

        // Wrong password.

        LoginRequest badLoginRequest = new LoginRequest();
        badLoginRequest.setUsername("wrong");
        badLoginRequest.setPassword("password");

        given(userRepository.findByUsername("wrong")).willReturn(user);

        // when
        String result2 = "";
        try {
            result2 = loginService.login(badLoginRequest);
        } catch (Exception e) {
            assertEquals(ApplicationException.class, e.getClass());
        }
    }
}