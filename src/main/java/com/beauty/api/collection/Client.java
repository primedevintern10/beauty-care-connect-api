package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "Client")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Client {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String email;
    @Builder.Default
    private Boolean isAnonymous = false;
    private Address address;
}
