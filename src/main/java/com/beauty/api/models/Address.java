package com.beauty.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String no;
    private String street;
    private String city;
    private String country;
    private String postalCode;
}
