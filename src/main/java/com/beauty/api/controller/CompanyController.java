package com.beauty.api.controller;

import com.beauty.api.collection.Company;
import com.beauty.api.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@Tag(name = "Companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Create a Company")
    @PostMapping
    public String save(@RequestBody Company company) {
        return companyService.save(company);
    }

    @Operation(summary = "Get All Companies")
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @Operation(summary = "Get Company by ID")
    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(@PathVariable("id") String companyId) {
        return companyService.getCompanyById(companyId);
    }

    @Operation(summary = "Update a Company")
    @PutMapping("/{id}")
    public Company update(@PathVariable("id") String companyId, @RequestBody Company company) {
        return companyService.update(company, companyId);
    }

    @Operation(summary = "Remove a Company")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String companyId) {
        companyService.delete(companyId);
        return "Deleted Successfully";
    }
}
