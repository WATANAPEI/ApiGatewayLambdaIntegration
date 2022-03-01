package dev.wpei.sc_review;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ReviewLambdaIntegratedHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context)
    {
        String body = event.getBody();
//        event.setTitle(event.getTitle()+" にlambdaで追記");
//        event.setTitleDetail(event.getTitleDetail()+" にlambdaで追記");
//        event.setCoverImageUrl(event.getCoverImageUrl()+" にlambdaで追記");
//        event.setBackgroundImageUrl(event.getBackgroundImageUrl()+" にlambdaで追記");
        ObjectMapper mapper = new ObjectMapper();
        String eventString = "";
        try {
            eventString = mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        response.setHeaders(headers);
        response.setBody("{\"test\": \"body\"}");

        LambdaLogger logger = context.getLogger();
        // process event
        logger.log("event: " + eventString);
        logger.log("body: " + body);
        logger.log("EVENT TYPE: " + event.getClass().toString());
        return response;
    }
}