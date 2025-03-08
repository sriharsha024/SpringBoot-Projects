package com.project.LibraryMgmt.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDTO {
    private long authorId;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorDescription;
}
