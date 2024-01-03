package com.beauty.api.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "User")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String userName;
    private String nicPassport;
    private String email;
    private String contactNo;
    @JsonIgnore
    private String password;
    @DBRef
    private UserGroup userGroup;
}
