package com.home.work.wheatherapp.domain
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.home.work.wheatherapp.util.CustomDeserializer
import java.time.LocalDateTime



class WeatherRequest {

    private Long id

    private String address

    private String type

    private float latitude

    private float longitude

    @JsonDeserialize(using = CustomDeserializer.class)
    private LocalDateTime datetime

    private String incidentNumber

    private String zip

}
