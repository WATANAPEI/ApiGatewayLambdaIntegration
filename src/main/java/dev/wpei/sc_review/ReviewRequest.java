package dev.wpei.sc_review;

import lombok.Data;

@Data
public class ReviewRequest {
    private String title;
    private String titleDetail;
    private String coverImageUrl;
    private String backgroundImageUrl;
}
