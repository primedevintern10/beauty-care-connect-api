package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "Service")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Service {
    @Id
    private String _id;
    private String name;
    private String requiredTime;
    private Boolean isEnabled;
    @DBRef
    private ServiceCategory serviceCategory;
    @DBRef
    private List<Branch> branch;
}
