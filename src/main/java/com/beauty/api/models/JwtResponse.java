package com.beauty.api.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String _id;
    private String username;
    private String jwtToken;
}
