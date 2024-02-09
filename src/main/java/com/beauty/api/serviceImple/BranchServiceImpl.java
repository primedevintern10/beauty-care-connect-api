package com.beauty.api.serviceImple;

import com.beauty.api.collection.Branch;
import com.beauty.api.repository.BranchRepository;
import com.beauty.api.service.AppointmentService;
import com.beauty.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        calculateBranchRatings(branches); // Calculate ratings for each branch
        return branches;
    }

    @Override
    public Optional<Branch> getBranchById(String branchId) {
        return branchRepository.findById(branchId);
    }

    @Override
    public boolean delete(String branchId) {
        branchRepository.deleteById(branchId);
        return false;
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

            return branchRepository.save(existingBranchData);
        } else {
            return null;
        }
    }

    @Override
    public List<Branch> getBranchesByCompanyId(String companyId) {
        return branchRepository.findByCompanyId(companyId);
    }

    private void calculateBranchRatings(List<Branch> branches) {
        for (Branch branch : branches) {
            int totalRating = appointmentService.calculateTotalRatingForBranch(branch.get_id());
            int totalAppointments = appointmentService.getTotalCompletedAppointmentsForBranch(branch.get_id());
            branch.setRating(totalAppointments > 0 ? totalRating / totalAppointments : 0);
        }
    }
}
