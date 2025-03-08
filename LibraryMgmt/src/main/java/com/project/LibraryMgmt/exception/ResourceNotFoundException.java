package com.project.LibraryMgmt.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String field;
    private String fieldName;
    private Long fieldId;

    public ResourceNotFoundException(String field, String fieldName, String resourceName) {
        super(String.format("%s not found in %s: %s", resourceName, field, fieldName));
        this.field = field;
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String field, String resourceName, Long fieldId) {
        super(String.format("%s not found in %s with ID: %d", resourceName, field, fieldId));
        this.field = field;
        this.fieldId = fieldId;
        this.resourceName = resourceName;
    }
}
