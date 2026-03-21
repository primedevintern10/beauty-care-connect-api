package com.beauty.api.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "PasswordResetToken")
public class PasswordResetToken {
    @Id
    private String _id;
    private String email;
    private String username;
    private String token;
    private Date createdAt;
    private Date expiresAt;
    private Boolean used;
}
