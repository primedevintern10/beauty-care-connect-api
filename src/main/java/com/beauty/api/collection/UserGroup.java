package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "UserGroup")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGroup {
    private String _id;
    private String name;
    private List<String> permission;
}
