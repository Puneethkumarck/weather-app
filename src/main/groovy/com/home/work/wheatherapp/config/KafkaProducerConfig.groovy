package com.home.work.wheatherapp.config
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@EnableKafka
@Configuration
class KafkaProducerConfig {

    @Value('${bootstrap.servers}')
    private String bootStrapServers

    @Value('${producer.retries}')
    int producerRetries

    @Value('${producer.batch.size}')
    int batchSize

    @Bean
    Map<String,Object> producerConfig(){
        Map<String,Object> properties=[:]
        /**kafka broker/server address **/
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers)
        /**producer retry config in case of failures**/
        properties.put(ProducerConfig.RETRIES_CONFIG,producerRetries)
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,1)
        /** group together request with same partition  size in bytes**/
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384)
        /** This config is to wait @ producer end to group together the request in batch **/
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1)
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class)
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class)

        properties
    }

    @Bean
    ProducerFactory<Integer,String> producerFactory(){
        return new DefaultKafkaProducerFactory<Integer, String>(producerConfig())
    }


    @Bean
    KafkaTemplate<Integer,String> kafkaTemplate(){
        return new KafkaTemplate<Integer, String>(producerFactory())
    }
}
