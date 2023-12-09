package com.beauty.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyEntityDTO {
	private String id;

    private String name;
    private int age;
}
