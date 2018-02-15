package com.home.work.wheatherapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.retry.annotation.EnableRetry


@EnableFeignClients
@EnableRetry
@EnableCircuitBreaker
@SpringBootApplication
class WeatherAppApplication {

	static void main(String[] args) {
		SpringApplication.run WeatherAppApplication, args
	}
}
