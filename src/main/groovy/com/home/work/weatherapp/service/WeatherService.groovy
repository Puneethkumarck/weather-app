package com.home.work.weatherapp.service

import com.home.work.weatherapp.domain.WeatherRequest
import com.home.work.weatherapp.domain.WeatherResponse
import com.home.work.weatherapp.repository.WeatherServiceDao
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
@Slf4j
class WeatherService {


    @Value('${service.name}') private String serviceName

    @Autowired private WeatherServiceCircuit weatherServiceCircuit

    @Value('${app.id}') private String appID


    @Value('${app.secret}') private String appSecret

    @Autowired private WeatherServiceDao weatherServiceDao

    void process(WeatherRequest weatherRequest){

        float lat = weatherRequest.latitude

        float lag = weatherRequest.longitude

        LocalDateTime localDateTime= weatherRequest.datetime

        WeatherResponse weatherResponse


        if(lat == 0.0f && lag == 0.0f){
            log.warn('Blank Coordinates for serviceName {} and incident number is {}',serviceName,weatherRequest.incidentNumber )
            weatherResponse=new WeatherResponse()
        } else {
            weatherResponse = weatherServiceCircuit.processWeatherResponse(lat,lag,appID,appSecret)

            if(!weatherResponse){
                log.error("Service: {}. Incident: {}. Can not fetch Weather data!", serviceName, weatherRequest.incidentNumber)
                weatherResponse = new WeatherResponse()
            }

            weatherResponse.incidentNumber=weatherRequest.incidentNumber
            weatherResponse.datetime= localDateTime
            weatherResponse.zip=weatherRequest.zip

            log.info("Service: {}. Incident: {}. Fetched weatherRecord Object {}", serviceName, weatherRequest.incidentNumber, weatherResponse)

            weatherServiceDao.insertCassandra(weatherResponse)

        }

    }
}
