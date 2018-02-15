package com.home.work.wheatherapp.repository

import com.home.work.wheatherapp.domain.WeatherResponse
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service


@Service
@Slf4j
class WeatherServiceDao {

    @Autowired CassandraTemplate cassandraTemplate

    void insertCassandra(WeatherResponse weatherResponse) {

        try {
            log.info("CASSANDRA insertion is triggered for record ${weatherResponse}")
            cassandraTemplate.insert(weatherResponse)
        }catch (Exception e){
            log.error("Exception occurred while processing cassandra record",e)
        }
    }
}
