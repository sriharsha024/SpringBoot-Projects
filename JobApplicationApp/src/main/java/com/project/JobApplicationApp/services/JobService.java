package com.project.JobApplicationApp.services;

import com.project.JobApplicationApp.entity.Job;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(Long id);
    Job createJob(Job job);
    Job updateJob(Long id, @Valid Job job);
    void deleteJob(Long id);
}
