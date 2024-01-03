package com.beauty.api.repository;

import com.beauty.api.collection.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<Branch, String> {
}
