package com.beauty.api.repository;

import com.beauty.api.collection.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BranchRepository extends MongoRepository<Branch, String> {
    @Query("{ 'company._id' : ?0 }")
    List<Branch> findByCompanyId(String companyId);

    @Query("{ 'address.city' : ?0 }")
    List<Branch> findByLocation(String location);
}
