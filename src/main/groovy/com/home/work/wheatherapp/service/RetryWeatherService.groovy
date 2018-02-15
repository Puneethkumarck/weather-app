package com.home.work.wheatherapp.service
import com.home.work.wheatherapp.domain.WeatherResponse
import com.home.work.wheatherapp.exception.WeatherServiceException
import feign.FeignException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service


@Service
@Slf4j
class RetryWeatherService {


    @Autowired
    WeatherFeignCall weatherFeignCall


    @Retryable(value = [FeignException.class] , maxAttempts = 4)
    WeatherResponse getWeatherResponse(float lang,float lat,String appID,String appSecret){
        WeatherResponse weatherResponse= weatherFeignCall.currentWeather(lang,lat,appID,appSecret)
        weatherResponse
    }


    @Recover
    WeatherResponse wetaherRetryFallBack(FeignException e){
        log.warn("Weather service is down we have attempted 5 retries ")
        throw new WeatherServiceException()
    }
}
