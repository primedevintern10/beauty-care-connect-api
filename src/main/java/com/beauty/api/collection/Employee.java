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
@Document(collection = "Employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    @Id
    private String _id;
    private String nic;
    private String name;
    private String nickName;
    private String email;
    private String contactNo;
    private Boolean isEnabled;
    private String type;

    @DBRef
    private List<Branch> branch;
}
