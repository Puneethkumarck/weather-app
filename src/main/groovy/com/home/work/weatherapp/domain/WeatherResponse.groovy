package com.home.work.weatherapp.domain

import org.apache.commons.lang.builder.ToStringBuilder
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table
import java.time.LocalDateTime


@Table("weather_details")
class WeatherResponse {

    @PrimaryKeyColumn(name='incident_number',ordinal =0,type = PrimaryKeyType.PARTITIONED)
     String incidentNumber

    @Column('temp')
     Float temperature

    @Column('condition_desc')
     String conditionDescription

    @PrimaryKeyColumn(name='date_time',ordinal =1,type = PrimaryKeyType.CLUSTERED)
     String datetime

    @Column('zip')
     String zip

    @Column('latitude')
    float latitude

    @Column('longitude')
    float longitude


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("incidentNumber", incidentNumber)
                .append("temperature", temperature)
                .append("conditionDescription", conditionDescription)
                .append("datetime", datetime)
                .append("zip", zip)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}
