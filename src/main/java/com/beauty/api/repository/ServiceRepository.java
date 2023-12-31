package com.beauty.api.repository;

import com.beauty.api.collection.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRepository extends MongoRepository<Service, String> {
}
