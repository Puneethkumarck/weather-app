package com.home.work.weatherapp.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime


@Component
class CustomDeserializer extends  StdScalarDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 668689695987281122L

    CustomDeserializer() {
        super(LocalDateTime.class)
    }

    @Override
    LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        long unixSeconds = jsonParser.getValueAsLong()
        Date date = new Date(unixSeconds*1000L)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
        String formattedDate = sdf.format(date)
        return LocalDateTime.parse(formattedDate)
    }
}