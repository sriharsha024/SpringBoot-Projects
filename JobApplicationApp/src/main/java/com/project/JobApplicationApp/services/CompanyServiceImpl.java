package com.project.JobApplicationApp.services;

import com.project.JobApplicationApp.entity.Company;
import com.project.JobApplicationApp.entity.Job;
import com.project.JobApplicationApp.exceptions.ResourceNotFoundException;
import com.project.JobApplicationApp.repo.CompanyRepo;
import com.project.JobApplicationApp.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private JobRepo jobRepo;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
    }

    @Override
    public Company addCompany(Company company) {
        if (company.getJobs() != null) {
            for (Job job : company.getJobs()) {
                job.setCompany(company);
            }
        }
        return companyRepo.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Company companyFromDB = companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
        companyFromDB.setCompanyName(company.getCompanyName());
        companyFromDB.setCompanyAddress(company.getCompanyAddress());
        companyFromDB.setCompanyPhone(company.getCompanyPhone());
        companyFromDB.setCompanyEmail(company.getCompanyEmail());
        companyFromDB.setJobs(company.getJobs());
        return companyRepo.save(companyFromDB);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
        companyRepo.delete(company);
    }
}