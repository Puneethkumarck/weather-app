package com.home.work.weatherapp.service

import com.home.work.weatherapp.domain.WeatherResponse
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant


@Service
@Slf4j
class WeatherServiceCircuit {

    @Autowired
    RetryWeatherService retryWeatherService


    @Value('${service.name}')
    String ServiceName

    @HystrixCommand(fallbackMethod = "processWeatherFallback")
    WeatherResponse processWeatherResponse(float lang, float lat, String appID, String appSecret){
        retryWeatherService.getWeatherResponse(lang,lat,appID,appSecret)
    }


    WeatherResponse processWeatherFallback(String lang,String lat,String appID,String appSecret){
        log.warn('Circuit Breaker Fallback method for the service {} and time is {} ', serviceName,Instant.now())
        null
    }
}
