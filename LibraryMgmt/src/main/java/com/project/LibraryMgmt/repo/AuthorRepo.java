package com.project.LibraryMgmt.repo;

import com.project.LibraryMgmt.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthorName(String name);

    // If multiple authors can have the same name
    @Query("SELECT a FROM Author a WHERE a.authorName = :name")
    List<Author> findAllByAuthorName(@Param("name") String name);

}
