package com.project.JobApplicationApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    @NotNull
    private String jobName;

    @NotNull
    private String jobDescription;

    @NotNull
    private int minExperience;

    @NotNull
    private double minSalary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnoreProperties("jobs")
    private Company company;
}