package com.project.LibraryMgmt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;

    @NotNull
    @Column(unique = true)
    private String publisherName;

    @ManyToMany(mappedBy = "publishers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Book> books = new HashSet<>();

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }
}
