package com.beauty.api.collection;

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
    private String _id;
    private String nicPassport;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String contactNo;
    private String password;

    // UserGroup is embedded here
    @DBRef
    private UserGroup userGroup;
}
