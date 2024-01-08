package com.beauty.api.repository;

import com.beauty.api.collection.ServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCategoryRepository extends MongoRepository<ServiceCategory,String>  {
}
