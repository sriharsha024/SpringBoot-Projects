package com.project.LibraryMgmt.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class BookDTO {
    private int bookId;

    @NotBlank
    private String isbn;

    @NotBlank
    private String bookName;

    @NotBlank
    private String bookDescription;

    private Set<String> authorNames;  // Now taking names instead of IDs
    private Set<String> categoryNames;
    private Set<String> publisherNames;
}
