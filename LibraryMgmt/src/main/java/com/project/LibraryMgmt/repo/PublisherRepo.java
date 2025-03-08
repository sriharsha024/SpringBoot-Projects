package com.project.LibraryMgmt.repo;

import com.project.LibraryMgmt.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByPublisherName(String publisherName);

    // If multiple publishers can have the same name
    @Query("SELECT p FROM Publisher p WHERE p.publisherName = :publisherName")
    List<Publisher> findAllByPublisherName(@Param("publisherName") String publisherName);
}
