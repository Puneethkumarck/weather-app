package com.home.work.weatherapp.domain

import org.apache.commons.lang.builder.ToStringBuilder

class WeatherData {

    String country
    String city
    String  accentCity
    String  region
    String population
    String  latitude
    String longitude


    @Override
    String toString() {
        return new ToStringBuilder(this)
                .append("country", country)
                .append("city", city)
                .append("accentCity", accentCity)
                .append("region", region)
                .append("population", population)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString()
    }
}

