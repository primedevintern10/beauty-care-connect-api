package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "AppointmentStatus")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentStatus {
    @Id
    private String _id;
    private String status;
    private Boolean isEnabled;
}
