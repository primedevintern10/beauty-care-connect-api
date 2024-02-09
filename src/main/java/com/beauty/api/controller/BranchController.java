package com.beauty.api.controller;

import com.beauty.api.collection.Branch;
import com.beauty.api.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/branch")
@Tag(name = "Branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Operation(summary = "Create a Branch")
    @PostMapping
    public ResponseEntity<Branch> save(@RequestBody Branch branch) {
        Branch savedBranch = branchService.save(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Branches")
    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @Operation(summary = "Get Branch by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable("id") String branchId) {
        try {
            Branch branch = branchService.getBranchById(branchId).orElseThrow();
            return new ResponseEntity<>(branch, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a Branch")
    @PutMapping("/{id}")
    public ResponseEntity<Branch> update(@PathVariable("id") String branchId, @RequestBody Branch branch) {
        Branch updatedBranch = branchService.update(branch, branchId);
        if (updatedBranch != null) {
            return ResponseEntity.ok(updatedBranch);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Remove a Branch")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String branchId) {
        boolean isDeleted = branchService.delete(branchId);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found");
        }
    }

    @Operation(summary = "Filter Branches by Company Id")
    @GetMapping("/by-company/{companyId}")
    public ResponseEntity<List<Branch>> getBranchesByCompany(@PathVariable String companyId) {
        List<Branch> branches = branchService.getBranchesByCompanyId(companyId);
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @Operation(summary = "Filter Branches by Location")
    @GetMapping("/by-location/{location}")
    public ResponseEntity<List<Branch>> getBranchesByLocation(@PathVariable String location) {
        List<Branch> branches = branchService.getBranchesByLocation(location);
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
