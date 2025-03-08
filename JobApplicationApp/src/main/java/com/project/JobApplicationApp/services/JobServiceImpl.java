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
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));
    }

    @Override
    public Job createJob(Job job) {
        if (job.getCompany() == null || job.getCompany().getCompanyId() == 0) {
            throw new IllegalArgumentException("Company ID is mandatory when adding a job.");
        }
        Company company = companyRepo.findById(job.getCompany().getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", job.getCompany().getCompanyId()));
        job.setCompany(company);
        return jobRepo.save(job);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job jobFromDB = jobRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));
        jobFromDB.setJobName(job.getJobName());
        jobFromDB.setJobDescription(job.getJobDescription());
        jobFromDB.setMinSalary(job.getMinSalary());
        jobFromDB.setMinExperience(job.getMinExperience());
        return jobRepo.save(jobFromDB);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));
        jobRepo.delete(job);
    }
}
