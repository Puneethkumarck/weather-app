package com.home.work.weatherapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.retry.annotation.EnableRetry


@EnableFeignClients
@EnableRetry
@EnableCircuitBreaker
@SpringBootApplication
@ComponentScan(['com.home.work.weatherapp.*'])
class WeatherAppApplication {

	static void main(String[] args) {
		SpringApplication.run WeatherAppApplication, args
	}
}
