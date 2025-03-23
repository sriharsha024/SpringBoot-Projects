package com.project.LibraryMgmt.service;

import com.project.LibraryMgmt.entity.Author;
import com.project.LibraryMgmt.entity.Book;
import com.project.LibraryMgmt.entity.Category;
import com.project.LibraryMgmt.entity.Publisher;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import com.project.LibraryMgmt.payload.BookDTO;
import com.project.LibraryMgmt.payload.BookResponse;
import com.project.LibraryMgmt.repo.AuthorRepo;
import com.project.LibraryMgmt.repo.BookRepo;
import com.project.LibraryMgmt.repo.CategoryRepo;
import com.project.LibraryMgmt.repo.PublisherRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PublisherRepo publisherRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(this::convertToBookResponse).collect(Collectors.toList());
    }

    public BookResponse getBookById(long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return convertToBookResponse(book);
    }

    public BookResponse createBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setBookName(bookDTO.getBookName());
        book.setBookDescription(bookDTO.getBookDescription());

        Set<Author> authors = new HashSet<>();
        for (String s : bookDTO.getAuthorNames()) {
            Object o = authorRepo.findByAuthorName(s)
                    .orElseThrow(() -> new ResourceNotFoundException("Author", "name", s));
            authors.add((Author) o);
        }

        Set<Category> categories = new HashSet<>();
        for (String s : bookDTO.getCategoryNames()) {
            Object o = categoryRepo.findByCategoryName(s)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "name", s));
            categories.add((Category) o);
        }

        Set<Publisher> publishers = new HashSet<>();
        for (String name : bookDTO.getPublisherNames()) {
            Object o = publisherRepo.findByPublisherName(name)
                    .orElseThrow(() -> new ResourceNotFoundException("Publisher", "name", name));
            publishers.add((Publisher) o);
        }

        book.setAuthors(authors);
        book.setCategories(categories);
        book.setPublishers(publishers);

        // Save book
        book = bookRepo.save(book);

        return convertToBookResponse(book);
    }

    public BookResponse updateBook(long id, BookDTO bookDTO) {
        Book bookFromDB = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        bookFromDB.setIsbn(bookDTO.getIsbn());
        bookFromDB.setBookName(bookDTO.getBookName());
        bookFromDB.setBookDescription(bookDTO.getBookDescription());

        // Fetching authors
        Set<Author> authors = new HashSet<>();
        for (String name : bookDTO.getAuthorNames()) {
            List<Author> authorList = authorRepo.findAllByAuthorName(name);
            if (authorList.isEmpty()) {
                throw new ResourceNotFoundException("Author", "name", name);
            }
            authors.addAll(authorList);
        }

        // Fetching categories
        Set<Category> categories = new HashSet<>();
        for (String name : bookDTO.getCategoryNames()) {
            List<Category> categoryList = categoryRepo.findAllByCategoryName(name);
            if (categoryList.isEmpty()) {
                throw new ResourceNotFoundException("Category", "name", name);
            }
            categories.addAll(categoryList);
        }

        // Fetching publishers
        Set<Publisher> publishers = new HashSet<>();
        for (String name : bookDTO.getPublisherNames()) {
            List<Publisher> publisherList = publisherRepo.findAllByPublisherName(name);
            if (publisherList.isEmpty()) {
                throw new ResourceNotFoundException("Publisher", "name", name);
            }
            publishers.addAll(publisherList);
        }

        // Updating book with new data
        bookFromDB.setAuthors(authors);
        bookFromDB.setCategories(categories);
        bookFromDB.setPublishers(publishers);

        // Save updated book
        Book updatedBook = bookRepo.save(bookFromDB);

        return convertToBookResponse(updatedBook);
    }


    public boolean deleteBook(long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        bookRepo.delete(book);
        return true;
    }

    // Helper method to convert Book to BookResponse
    private BookResponse convertToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId((int) book.getBookId());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setBookName(book.getBookName());
        bookResponse.setBookDescription(book.getBookDescription());

        bookResponse.setAuthorNames(book.getAuthors().stream()
                .map(Author::getAuthorName)
                .collect(Collectors.toSet()));
        bookResponse.setCategoryNames(book.getCategories().stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toSet()));
        bookResponse.setPublisherNames(book.getPublishers().stream()
                .map(Publisher::getPublisherName)
                .collect(Collectors.toSet()));

        return bookResponse;
    }
}