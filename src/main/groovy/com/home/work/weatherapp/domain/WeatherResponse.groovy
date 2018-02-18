package com.home.work.weatherapp.domain
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
    String latitude

    @Column('longitude')
    String longitude


    @Override
    String toString() {
        return "WeatherResponse{" +
                "incidentNumber='" + incidentNumber + '\'' +
                ", temperature=" + temperature +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", datetime=" + datetime +
                ", zip='" + zip + '\'' +
                '}'
    }
}
