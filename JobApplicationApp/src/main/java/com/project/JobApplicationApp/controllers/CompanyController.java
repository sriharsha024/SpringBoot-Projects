package com.project.JobApplicationApp.controllers;

import com.project.JobApplicationApp.entity.Company;
import com.project.JobApplicationApp.exceptions.ResourceNotFoundException;
import com.project.JobApplicationApp.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company) {
        Company savedCompany = companyService.addCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }
}