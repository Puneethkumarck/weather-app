package com.home.work.weatherapp.service

import com.home.work.weatherapp.domain.WeatherRequest
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
@Slf4j
class WeatherRequestListener {

    @Autowired
    WeatherService weatherService

    @KafkaListener(topics = ['${kafka.weather.topic}'])
    void processWeatherRequest(WeatherRequest weatherRequest){

        try {
            weatherService.process(weatherRequest)
        }catch (Exception e){
            log.error("Exception occurred while processing kafka listener message for given request ${weatherRequest}",e)
        }
    }
}
