package com.project.LibraryMgmt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotNull
    @Column(unique = true)
    private String isbn;

    @NotNull
    @Column(unique = true)
    private String bookName;

    @NotNull
    private String bookDescription;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "books_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<Publisher> publishers = new HashSet<>();


    public Book(int bookId, String isbn, String bookName, String bookDescription,
                Set<Author> authors, Set<Category> categories, Set<Publisher> publishers) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.authors = authors != null ? authors : new HashSet<>();
        this.categories = categories != null ? categories : new HashSet<>();
        this.publishers = publishers != null ? publishers : new HashSet<>();
    }
    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn, bookName, bookDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return bookId == book.bookId &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(bookName, book.bookName) &&
                Objects.equals(bookDescription, book.bookDescription);
    }

}
