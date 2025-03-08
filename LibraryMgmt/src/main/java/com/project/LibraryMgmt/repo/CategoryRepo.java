package com.project.LibraryMgmt.repo;

import com.project.LibraryMgmt.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);

    // If multiple categories can have the same name
    @Query("SELECT c FROM Category c WHERE c.categoryName = :categoryName")
    List<Category> findAllByCategoryName(@Param("categoryName") String categoryName);
}
