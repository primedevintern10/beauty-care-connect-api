package com.beauty.api.service;

import com.beauty.api.collection.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company save(Company company);

    List<Company> getAllCompanies();

    Optional<Company> getCompanyById(String companyId);

    boolean delete(String companyId);

    Company update(Company company, String companyId);

    long getTotalCompanyCount();
}
