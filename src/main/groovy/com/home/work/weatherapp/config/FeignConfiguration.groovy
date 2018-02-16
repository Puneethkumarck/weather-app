package com.home.work.weatherapp.config
import feign.Logger
import feign.Request
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    private static final int FIVE_SECONDS = 5000

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.BASIC
    }

    @Bean
    Request.Options options() {
        return new Request.Options(FIVE_SECONDS, FIVE_SECONDS)
    }
}
