package com.beauty.api.collection;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtRequest {
    private String username;
    private String password;
}
