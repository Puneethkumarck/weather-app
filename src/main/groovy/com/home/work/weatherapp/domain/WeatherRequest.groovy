package com.home.work.weatherapp.domain
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.home.work.weatherapp.util.CustomDeserializer
import org.apache.commons.lang.builder.ToStringBuilder

import java.time.LocalDateTime



class WeatherRequest {

     Long id

     String address

     String type

     float latitude

     float longitude

     String datetime

     String incidentNumber

     String zip

    WeatherRequest() {

    }

    @Override
    String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("address", address)
                .append("type", type)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("datetime", datetime)
                .append("incidentNumber", incidentNumber)
                .append("zip", zip)
                .toString()
    }
}
