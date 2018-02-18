package com.home.work.weatherapp.service

import com.home.work.weatherapp.domain.WeatherResponse
import com.home.work.weatherapp.exception.WeatherServiceException
import feign.FeignException
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
@Slf4j
class RetryWeatherService {


    @Autowired
    WeatherFeignCall weatherFeignCall


    @Autowired RestTemplate restTemplate

    @Value('${weather.base.uri}') String baseUri


    //curl -v http://api.weatherunlocked.com/api/current/77.594563,12.971599?app_id=750e67dd&app_key=726b82aa10e638380ca3f762d778bf5b

    @Retryable(value = [Exception.class] , maxAttempts = 4)
    WeatherResponse getWeatherResponse(float lon, float lat, String app_id, String app_key){
        log.info("making feign call method processing weather request for lat  ${lat} and ${lon}")
        WeatherResponse weatherResponse= new WeatherResponse()
        Map result= restTemplate.getForObject(getCurrentWeatherUrl(lon,lat,app_id,app_key),Map.class)
        log.info("feign call response for weather request for lat  ${lat} and ${lon} is ${result}")
        weatherResponse.temperature=result.'temp_c'
        weatherResponse.conditionDescription=result.wx_desc
        weatherResponse
    }


    @Recover
    WeatherResponse wetaherRetryFallBack(Exception e){
        log.warn("Weather service is down we have attempted 5 retries ")
        throw new WeatherServiceException()
    }

    String getCurrentWeatherUrl(float lon,float lat,String app_id,String app_key){

        "${baseUri}$lat,$lon?app_id=$app_id&app_key=$app_key"
    }
}
