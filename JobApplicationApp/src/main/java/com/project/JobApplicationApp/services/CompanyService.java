package com.project.JobApplicationApp.services;

import com.project.JobApplicationApp.entity.Company;
import jakarta.validation.Valid;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Company addCompany(@Valid Company company);
    Company updateCompany(Long id, @Valid Company company);
    void deleteCompany(Long id);
}
