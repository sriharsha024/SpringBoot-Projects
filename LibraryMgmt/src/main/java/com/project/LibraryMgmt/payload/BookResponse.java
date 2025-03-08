package com.project.LibraryMgmt.payload;

import lombok.Data;
import java.util.Set;

@Data
public class BookResponse {
    private int bookId;
    private String isbn;
    private String bookName;
    private String bookDescription;
    private Set<String> authorNames;
    private Set<String> categoryNames;
    private Set<String> publisherNames;
}
