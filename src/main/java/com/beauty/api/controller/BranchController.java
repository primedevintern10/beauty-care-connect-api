package com.beauty.api.controller;

import com.beauty.api.collection.Branch;
import com.beauty.api.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping
    public String save(@RequestBody Branch branch) {
        return branchService.save(branch);
    }

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public Optional<Branch> getBranchById(@PathVariable("id") String branchId) {
        return branchService.getBranchById(branchId);
    }

    @PutMapping("/{id}")
    public Branch update(@PathVariable("id") String branchId, @RequestBody Branch branch) {
        return branchService.update(branch, branchId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String branchId) {
        branchService.delete(branchId);
        return "Deleted Successfully";
    }
}
