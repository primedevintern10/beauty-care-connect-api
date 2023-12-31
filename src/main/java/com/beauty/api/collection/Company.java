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
@Document(collection = "Company")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    @Id
    private String _id;
    private String name;
    private String registrationNo;
    private String owner;
    private String email;
    private String webUrl;
    private String country;
    private String currency;
    @DBRef
    private List<Branch> Branches;
}
