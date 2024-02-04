package com.beauty.api.service;

import com.beauty.api.collection.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Branch save(Branch branch);

    List<Branch> getAllBranches();

    Optional<Branch> getBranchById(String branchId);

    void delete(String branchId);

    Branch update(Branch branch, String branchId);
}
