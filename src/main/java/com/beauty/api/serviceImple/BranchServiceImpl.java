package com.beauty.api.serviceImple;

import com.beauty.api.collection.Branch;
import com.beauty.api.repository.BranchRepository;
import com.beauty.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public String save(Branch branch) {
        return branchRepository.save(branch).getName();
    }

    @Override
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Optional<Branch> getBranchById(String branchId) {
        return branchRepository.findById(branchId);
    }

    @Override
    public void delete(String branchId) {
        branchRepository.deleteById(branchId);
    }

    @Override
    public Branch update(Branch branch, String branchId) {
        Branch existingBranchData = branchRepository.findById(branchId).orElse(null);

        if (existingBranchData != null) {
            existingBranchData.setName(branch.getName());
            existingBranchData.setContactNo(branch.getContactNo());
            existingBranchData.setAddress(branch.getAddress());
            existingBranchData.setEmail(branch.getEmail());
            existingBranchData.setCompany(branch.getCompany());
//            existingBranchData.setServices(branch.getServices());
//            existingBranchData.setEmployees(branch.getEmployees());

            return branchRepository.save(existingBranchData);
        } else {
            return null;
        }
    }
}
