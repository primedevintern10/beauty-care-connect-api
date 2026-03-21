package com.beauty.api.serviceImple;

import com.beauty.api.collection.Company;
import com.beauty.api.repository.CompanyRepository;
import com.beauty.api.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(String companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public boolean delete(String companyId) {
        if (!companyRepository.existsById(companyId)) {
            return false;
        }

        companyRepository.deleteById(companyId);
        return true;
    }

    @Override
    public Company update(Company company, String companyId) {
        Company existingCompanyData = companyRepository.findById(companyId).orElse(null);

        if (existingCompanyData != null) {
            existingCompanyData.setName(company.getName());
            existingCompanyData.setRegistrationNo(company.getRegistrationNo());
            existingCompanyData.setOwner(company.getOwner());
            existingCompanyData.setEmail(company.getEmail());
            existingCompanyData.setWebUrl(company.getWebUrl());
            existingCompanyData.setCountry(company.getCountry());
            existingCompanyData.setCurrency(company.getCurrency());

            return companyRepository.save(existingCompanyData);
        } else {
            return null;
        }
    }

    @Override
    public long getTotalCompanyCount() {
        return companyRepository.count();
    }
}
