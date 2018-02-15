package com.home.work.wheatherapp.exception

import groovy.util.logging.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.listener.ErrorHandler
import org.springframework.stereotype.Component

@Component
@Slf4j
class WeatherExceptionHandler implements ErrorHandler{

    @Override
    void handle(Exception e, ConsumerRecord<?, ?> consumerRecord) {
        log.error("Error occurred while processing consumer record for details topic=${consumerRecord.topic()} key=${consumerRecord.key()} partition=${consumerRecord.partition()} timestamp=${consumerRecord.timestamp()} ${consumerRecord.dump()} exceptionMessage=${e.message}")
    }
}
