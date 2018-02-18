package com.home.work.weatherapp.controller
import com.fasterxml.jackson.databind.ObjectMapper
import com.home.work.weatherapp.domain.WeatherRequest
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.concurrent.atomic.AtomicInteger

@Slf4j
@RequestMapping('/weather')
@RestController
class WeatherController {

    @Autowired KafkaTemplate kafkaTemplate

    @Value('${kafka.weather.topic}') String topic

    @Autowired ObjectMapper objectMapper

    AtomicInteger atomicInteger=new AtomicInteger()

   final static SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z")

    @Autowired ApplicationContext applicationContext

    @RequestMapping(value = '/details/{lang}/{lat}', produces = "application/json")
    String getWeatherDetails(@PathVariable float lang, @PathVariable float lat){

        try{

            WeatherRequest weatherRequest=new WeatherRequest()
            weatherRequest.latitude=lang
            weatherRequest.longitude=lat
            weatherRequest.incidentNumber=atomicInteger.incrementAndGet()
            SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z")
            weatherRequest.datetime=dateFormatter.format(new Date())

            ListenableFuture<SendResult<Integer,String>> future = kafkaTemplate.send(topic,objectMapper.writeValueAsString(weatherRequest))

            future.addCallback(new ListenableFutureCallback<SendResult>() {

                @Override
                void onFailure(Throwable throwable) {
                    log.error("Error occurred while sending login history data to kafka ${throwable}")
                    "FAILURE"
                }

                @Override
                void onSuccess(SendResult sendResult) {
                    log.info("Success Callback --> Success On sending weather request event to topic=${topic} with message=${sendResult} offset=${sendResult.recordMetadata.offset()} partition=${sendResult.recordMetadata.partition()}")
                    "SUCCESS"
                }
            })

        }catch (Exception e){
            log.error("Exception occurred while sending weather request ",e)
        }

    }




    @RequestMapping(value = '/details/{filename}', produces = "application/json")
    String getWeatherDetailsForInputFile(@PathVariable String filename){

        try{

            String filePath="/weatherdata/${filename}.txt"
            log.info("input file path is ${filePath} for filename ${filename}")
            Resource resource = applicationContext.getResource("classpath:$filePath")
            log.info("filename from resource $resource.filename and uri ${resource.getURI()}")
            File file =new File(resource.getURI())
            def line
            file.withReader { reader ->
                while ((line = reader.readLine())!=null) {
                    WeatherRequest weatherRequest=new WeatherRequest()
                    getWeatherRequest(line as String,weatherRequest)
                    ListenableFuture<SendResult<Integer,String>> future = kafkaTemplate.send(topic,objectMapper.writeValueAsString(weatherRequest))
                    future.addCallback(new ListenableFutureCallback<SendResult>() {

                        @Override
                        void onFailure(Throwable throwable) {
                            log.error("Error occurred while sending weather request data to kafka ${throwable}")
                            "FAILURE"
                        }

                        @Override
                        void onSuccess(SendResult sendResult) {
                            log.info("Success Callback --> Success On sending weather request event to topic=${topic} with message=${sendResult} offset=${sendResult.recordMetadata.offset()} partition=${sendResult.recordMetadata.partition()}")
                            "SUCCESS"
                        }
                    })
                }
            }

        }catch (Exception e){
            log.error("Exception occurred while sending weather request ",e)
        }

    }

    WeatherRequest getWeatherRequest(String line,WeatherRequest weatherRequest){
        String[] eachLine=line.split(',')
        weatherRequest.latitude=eachLine[5] as float
        weatherRequest.longitude=eachLine[6] as float
        weatherRequest.datetime=dateFormatter.format(new Date())
        weatherRequest.incidentNumber=atomicInteger.incrementAndGet()
        weatherRequest
    }
}
