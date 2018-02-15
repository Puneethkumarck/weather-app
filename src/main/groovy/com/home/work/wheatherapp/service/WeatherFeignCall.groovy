package com.home.work.wheatherapp.service

import com.home.work.wheatherapp.config.FeignConfiguration
import com.home.work.wheatherapp.domain.WeatherResponse
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "weather-unlocked-service", url = "http://api.weatherunlocked.com/api", configuration = FeignConfiguration.class)
interface WeatherFeignCall {

    @RequestMapping(method = RequestMethod.GET, value = "/current/{lat},{lon}")
    WeatherResponse currentWeather(@PathVariable("lat") float lat, @PathVariable("lon") float lon, @RequestParam("app_id") String appId, @RequestParam("app_key") String appKey)

}
