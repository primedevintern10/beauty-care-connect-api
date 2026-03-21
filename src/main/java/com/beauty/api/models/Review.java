package com.beauty.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Integer rating;
    private String comment;
    private Boolean isEnabled;
    private String type;
}
