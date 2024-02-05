package com.beauty.api.controller;

import com.beauty.api.collection.Company;
import com.beauty.api.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Company> save(@RequestBody Company company) {
        Company savedCompany = companyService.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @Operation(summary = "Get All Companies")
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @Operation(summary = "Get Company by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") String companyId) {
        Optional<Company> company = companyService.getCompanyById(companyId);
        return company.map(value -> ResponseEntity.ok(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a Company")
    @PutMapping("/{id}")
    public ResponseEntity<Company> update(@PathVariable("id") String companyId, @RequestBody Company company) {
        Company updatedCompany = companyService.update(company, companyId);
        return (updatedCompany != null) ?
                ResponseEntity.ok(updatedCompany) :
                ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove a Company")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String companyId) {
        boolean isDeleted = companyService.delete(companyId);
        if (isDeleted) {
            return new ResponseEntity<>("Company deleted successfully", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }
}
