package com.project.LibraryMgmt.service;

import com.project.LibraryMgmt.entity.Author;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import com.project.LibraryMgmt.payload.AuthorDTO;
import com.project.LibraryMgmt.repo.AuthorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }


    public AuthorDTO getAuthorById(long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return modelMapper.map(author,AuthorDTO.class);
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        author = authorRepo.save(author);
        AuthorDTO newAuthor = modelMapper.map(author,AuthorDTO.class);
        return newAuthor;
    }

    public AuthorDTO updateAuthor(long id, AuthorDTO authorDTO) {
        Author authorFromDB = authorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorFromDB.setAuthorName(authorDTO.getAuthorName());
        authorFromDB.setAuthorDescription(authorDTO.getAuthorDescription());
        authorFromDB = authorRepo.save(authorFromDB);
        return modelMapper.map(authorFromDB, AuthorDTO.class);
    }

    public boolean deleteAuthor(long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        authorRepo.delete(author);
        return true;
    }
}
