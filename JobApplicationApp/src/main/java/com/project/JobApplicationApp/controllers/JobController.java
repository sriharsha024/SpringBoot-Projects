package com.project.JobApplicationApp.controllers;

import com.project.JobApplicationApp.entity.Job;
import com.project.JobApplicationApp.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job) {
        Job savedJob = jobService.createJob(job);
        return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody Job job) {
        Job updatedJob = jobService.updateJob(id, job);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }
}
