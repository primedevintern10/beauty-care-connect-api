package com.beauty.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mycollection")
public class MyEntity {
	@Id
	private String id;

    private String name;
    private int age;
}
