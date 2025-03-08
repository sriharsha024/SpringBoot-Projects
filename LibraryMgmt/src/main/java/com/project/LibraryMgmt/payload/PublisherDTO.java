package com.project.LibraryMgmt.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublisherDTO {
    private int publisherId;

    @NotBlank
    private String publisherName;
}
