package com.home.work.weatherapp.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class MainConfig {

    @Bean
    RestTemplate restTemplate(){
        final RestTemplate restTemplate=new RestTemplate()
        SimpleClientHttpRequestFactory requestFactory=new SimpleClientHttpRequestFactory()
        requestFactory.setBufferRequestBody(false)
        requestFactory.readTimeout=5000
        requestFactory.connectTimeout=5000
        restTemplate.setRequestFactory(requestFactory)
        restTemplate
    }



    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper=new ObjectMapper()
        objectMapper.with {
            configure(SerializationFeature.WRITE_NULL_MAP_VALUES,false)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false)
            configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false)
            configure(SerializationFeature.INDENT_OUTPUT,false)
            configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true)
            enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
            setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        }
        //objectMapper.registerModule(simpleModule())
        objectMapper.registerModule(new JSR310Module())
        objectMapper
    }
}


