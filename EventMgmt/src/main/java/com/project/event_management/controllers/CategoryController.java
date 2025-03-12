package com.project.event_management.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.event_management.model.Category;
import com.project.event_management.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
		return categoryService.getCategoryById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category categoryDetails) {
		Category createdCategory = categoryService.createCategory(categoryDetails);
		return ResponseEntity.status(201).body(createdCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category categoryDetails) {
		Optional<Category> updatedCategory = categoryService.updateCategory(id, categoryDetails);
		return updatedCategory
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
		boolean deleted = categoryService.deleteCategory(id);
		return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
