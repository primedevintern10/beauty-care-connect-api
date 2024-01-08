package com.beauty.api.controller;

import com.beauty.api.collection.Branch;
import com.beauty.api.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
@Tag(name = "Branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @Operation(summary = "Create a Branch")
    @PostMapping
    public String save(@RequestBody Branch branch) {
        return branchService.save(branch);
    }

    @Operation(summary = "Get All Branches")
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @Operation(summary = "Get Branch by ID")
    @GetMapping("/{id}")
    public Optional<Branch> getBranchById(@PathVariable("id") String branchId) {
        return branchService.getBranchById(branchId);
    }

    @Operation(summary = "Update a Branch")
    @PutMapping("/{id}")
    public Branch update(@PathVariable("id") String branchId, @RequestBody Branch branch) {
        return branchService.update(branch, branchId);
    }

    @Operation(summary = "Remove a Branch")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String branchId) {
        branchService.delete(branchId);
        return "Deleted Successfully";
    }
}
