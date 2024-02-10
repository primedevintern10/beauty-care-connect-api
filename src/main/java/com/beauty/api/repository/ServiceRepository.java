package com.beauty.api.repository;

import com.beauty.api.collection.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ServiceRepository extends MongoRepository<Service, String> {

    @Query("{ 'serviceCategory._id' : ?0 }")
    List<Service> findByCategoryId(String categoryId);

    @Query("{ 'branch._id' : ?0 }")
    List<Service> findByBranchId(String branchId);
}
