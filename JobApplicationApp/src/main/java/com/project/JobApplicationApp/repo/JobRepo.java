package com.project.JobApplicationApp.repo;

import com.project.JobApplicationApp.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {
}
