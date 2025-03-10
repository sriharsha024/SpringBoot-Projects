package com.projects.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String name;
    private Sys sys;
    private List<Weather> weather;
    private Main main;
    private Wind wind;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys {
        private String country;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weather {
        private int id;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {
        private double temp;
        private double humidity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {
        private double speed;

    }
}
