package com.project.LibraryMgmt.controller;

import com.project.LibraryMgmt.payload.PublisherDTO;
import com.project.LibraryMgmt.service.PublisherService;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // Get all publishers
    @GetMapping("/publishers")
    public ResponseEntity<List<PublisherDTO>> getPublishers() {
        return new ResponseEntity<>(publisherService.getAllPublishers(), HttpStatus.OK);
    }

    // Get a specific publisher by id
    @GetMapping("/publishers/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable long id) {
        return new ResponseEntity<>(publisherService.getPublisherById(id), HttpStatus.OK);
    }

    // Create a new publisher
    @PostMapping("/publishers")
    public ResponseEntity<PublisherDTO> createPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.createPublisher(publisherDTO), HttpStatus.CREATED);
    }

    // Update an existing publisher
    @PutMapping("/publishers/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable long id, @Valid @RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.updatePublisher(id, publisherDTO), HttpStatus.OK);
    }

    // Delete a publisher
    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable long id) {
        try {
            publisherService.deletePublisher(id);
            return new ResponseEntity<>("Publisher deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Publisher not found", HttpStatus.NOT_FOUND);
        }
    }
}
