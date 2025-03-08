package com.project.LibraryMgmt.controller;

import com.project.LibraryMgmt.payload.AuthorDTO;
import com.project.LibraryMgmt.service.AuthorService;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorDTO> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.createAuthor(authorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable long id, @RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.updateAuthor(id, authorDTO), HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable long id) {
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>("Author deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
    }
}
