package com.home.work.weatherapp.controller
import com.fasterxml.jackson.databind.ObjectMapper
import com.home.work.weatherapp.domain.WeatherRequest
import com.home.work.weatherapp.domain.WeatherResponse
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger

@Slf4j
@RequestMapping('/weather')
@RestController
class WeatherController {

    @Autowired KafkaTemplate kafkaTemplate

   @Value('${kafka.weather.topic}') String topic

    @Autowired ObjectMapper objectMapper

    AtomicInteger atomicInteger=new AtomicInteger()

    @RequestMapping(value = '/details/{lang}/{lat}', produces = "application/json")
    WeatherResponse getWeatherDetails(@PathVariable float lang, @PathVariable float lat){

        try{

            WeatherRequest weatherRequest=new WeatherRequest()
            weatherRequest.latitude=lang
            weatherRequest.longitude=lat
            weatherRequest.incidentNumber=atomicInteger.incrementAndGet()
            weatherRequest.datetime=LocalDateTime.now()

            ListenableFuture<SendResult<Integer,String>> future = kafkaTemplate.send(topic,objectMapper.writeValueAsString(weatherRequest))

            future.addCallback(new ListenableFutureCallback<SendResult>() {

                @Override
                void onFailure(Throwable throwable) {
                    log.error("Error occurred while sending login history data to kafka ${throwable}")
                }

                @Override
                void onSuccess(SendResult sendResult) {
                    log.info("Success Callback --> Success On sending weather request event to topic=${topic} with message=${sendResult} offset=${sendResult.recordMetadata.offset()} partition=${sendResult.recordMetadata.partition()}")
                }
            })

        }catch (Exception e){
            log.error("Exception occurred while sending weather request ")
        }

    }
}
