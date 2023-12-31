package com.beauty.api.controller;

import com.beauty.api.collection.Company;
import com.beauty.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public String save(@RequestBody Company company) {
        return companyService.save(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Optional<Company> getCompanyById(@PathVariable("id") String companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable("id") String companyId, @RequestBody Company company) {
        return companyService.update(company, companyId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String companyId) {
        companyService.delete(companyId);
        return "Deleted Successfully";
    }
}
