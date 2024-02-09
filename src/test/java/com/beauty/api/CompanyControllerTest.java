package com.beauty.api;

import com.beauty.api.collection.Company;
import com.beauty.api.controller.CompanyController;
import com.beauty.api.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCompanies() {
        Company company1 = Company.builder()._id("1").name("ABC Company").build();
        Company company2 = Company.builder()._id("2").name("XYZ Company").build();
        List<Company> companies = Arrays.asList(company1, company2);

        when(companyService.getAllCompanies()).thenReturn(companies);

        ResponseEntity<List<Company>> response = companyController.getAllCompanies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(companies, response.getBody());
    }

    @Test
    void testGetCompanyById() {
        String companyId = "1";
        Company company = Company.builder()._id(companyId).name("ABC Company").build();

        when(companyService.getCompanyById(companyId)).thenReturn(Optional.of(company));

        ResponseEntity<Company> response = companyController.getCompanyById(companyId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(company, response.getBody());
    }

    @Test
    void testGetCompanyByIdNotFound() {
        String companyId = "1";

        when(companyService.getCompanyById(companyId)).thenReturn(Optional.empty());

        ResponseEntity<Company> response = companyController.getCompanyById(companyId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}

