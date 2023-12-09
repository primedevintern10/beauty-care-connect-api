package com.beauty.api.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.beauty.api.entity.MyEntity;

public interface MyEntityRepository extends MongoRepository<MyEntity, String> {

}
