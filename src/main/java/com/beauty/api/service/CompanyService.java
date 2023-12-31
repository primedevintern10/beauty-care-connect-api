package com.beauty.api.service;

import com.beauty.api.collection.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    String save(Company company);

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(String companyId);

    void delete(String companyId);

    Company update(Company company, String companyId);
}
