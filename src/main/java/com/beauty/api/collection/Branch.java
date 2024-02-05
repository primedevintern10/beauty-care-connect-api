package com.beauty.api.collection;

import com.beauty.api.models.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "Branch")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Branch {
    @Id
    private String _id;
    private String name;
    private String contactNo;
    private Address address;
    private String email;

    @DBRef
    private Company company;
}
