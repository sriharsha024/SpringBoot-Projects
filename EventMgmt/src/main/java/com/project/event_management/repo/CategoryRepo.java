package com.project.event_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.event_management.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
