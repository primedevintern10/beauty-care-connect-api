package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "ServiceCategory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceCategory {
    @Id
    private String _id;
    private String name;
}
