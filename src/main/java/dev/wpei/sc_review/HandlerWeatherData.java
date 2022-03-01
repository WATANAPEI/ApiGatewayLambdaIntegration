package dev.wpei.sc_review;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerWeatherData implements RequestHandler<WeatherData, WeatherData>{
    @Override
    public WeatherData handleRequest(WeatherData event, Context context)
    {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        LambdaLogger logger = context.getLogger();
        // process event
        logger.log("EVENT: " + json);
        logger.log("EVENT TYPE: " + event.getClass().toString());
        return event;
    }
}