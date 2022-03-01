package dev.wpei.sc_review;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReviewHandler implements RequestHandler<ReviewRequest, ReviewRequest>{
    @Override
    public ReviewRequest handleRequest(ReviewRequest event, Context context)
    {
        event.setTitle(event.getTitle()+" にlambdaで追記");
        event.setTitleDetail(event.getTitleDetail()+" にlambdaで追記");
        event.setCoverImageUrl(event.getCoverImageUrl()+" にlambdaで追記");
        event.setBackgroundImageUrl(event.getBackgroundImageUrl()+" にlambdaで追記");
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