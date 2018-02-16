package com.home.work.weatherapp.domain
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table
import java.time.LocalDateTime


@Table("weather_details")
class WeatherResponse {

    @PrimaryKeyColumn(name = 'id', ordinal = 0,type=PrimaryKeyType.PARTITIONED)
    private Long id

    @Column('incident_number')
    private String incidentNumber

    @Column('temp')
    private Float temperature

    @Column('condition_desc')
    private String conditionDescription

    private LocalDateTime datetime

    private String zip


    @Override
    String toString() {
        return "WeatherResponse{" +
                "id=" + id +
                ", incidentNumber='" + incidentNumber + '\'' +
                ", temperature=" + temperature +
                ", conditionDescription='" + conditionDescription + '\'' +
                ", datetime=" + datetime +
                ", zip='" + zip + '\'' +
                '}'
    }
}
