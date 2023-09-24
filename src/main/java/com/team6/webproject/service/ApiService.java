package com.team6.webproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Service
@Slf4j
public class ApiService {

    public String getWeather(String city) throws IOException {
        URL url = null;
        if (Objects.equals(city, "London")) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=51.51&longitude=-0.13&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=Europe%2FLondon");
        } else if (Objects.equals(city, "Tallinn")) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=59.44&longitude=24.75&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=Europe%2FMoscow");
        } else if (Objects.equals(city, "New York")) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=40.71&longitude=-74.01&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=America%2FNew_York");
        } else if ("Tokyo".equals(city)) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=35.69&longitude=139.69&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=Asia%2FTokyo");
        } else if ("Sydney".equals(city)) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=-33.87&longitude=151.21&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=Australia%2FSydney");
        } else if ("Stockholm".equals(city)) {
            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=59.33&longitude=18.07&daily=temperature_2m_max,temperature_2m_min,sunrise,sunset,precipitation_hours&timezone=Europe%2FBerlin");
        }

        assert url != null;

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        log.info("Finished getWeather service");
        return content.toString();
    }

}
