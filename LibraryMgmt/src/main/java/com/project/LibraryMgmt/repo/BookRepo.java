package com.project.LibraryMgmt.repo;

import com.project.LibraryMgmt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo  extends JpaRepository<Book, Long> {
}
