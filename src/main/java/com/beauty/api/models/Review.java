package com.beauty.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
public class Review {
    private Integer rating;
    private String comment;
    private Boolean isEnabled;
    private String type;
}
