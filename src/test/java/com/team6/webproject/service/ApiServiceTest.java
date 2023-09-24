package com.team6.webproject.service;

import com.team6.webproject.repository.customer.Customer;
import com.team6.webproject.repository.establishment.Establishment;
import com.team6.webproject.repository.reservation.Reservation;
import com.team6.webproject.repository.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ApiServiceTest {
    @Spy
    private ApiService apiService;

    @Test
    void getWeather() throws IOException {
        given(apiService.getWeather("London")).willReturn("correct1");
        given(apiService.getWeather("Tallinn")).willReturn("correct2");
        given(apiService.getWeather("New York")).willReturn("correct3");
        given(apiService.getWeather("Tokyo")).willReturn("correct4");
        given(apiService.getWeather("Sydney")).willReturn("correct5");
        given(apiService.getWeather("Stockholm")).willReturn("correct6");

        // when
        String result1 = apiService.getWeather("London");
        String result2 = apiService.getWeather("Tallinn");
        String result3 = apiService.getWeather("New York");
        String result4 = apiService.getWeather("Tokyo");
        String result5 = apiService.getWeather("Sydney");
        String result6 = apiService.getWeather("Stockholm");

        // then
        assertEquals("correct1", result1);
        assertEquals("correct2", result2);
        assertEquals("correct3", result3);
        assertEquals("correct4", result4);
        assertEquals("correct5", result5);
        assertEquals("correct6", result6);
    }
}