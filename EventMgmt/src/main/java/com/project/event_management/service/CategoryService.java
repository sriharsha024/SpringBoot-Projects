package com.project.event_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event_management.model.Category;
import com.project.event_management.repo.CategoryRepo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	public Optional<Category> getCategoryById(long id) {
		return categoryRepo.findById(id);
	}

	public Category createCategory(Category categoryDetails) {
		return categoryRepo.save(categoryDetails);
	}

	public Optional<Category> updateCategory(long id, Category categoryDetails) {
		return categoryRepo.findById(id).map(existingCategory -> {
			categoryDetails.setId(id);
			return categoryRepo.save(categoryDetails);
		});
	}

	public boolean deleteCategory(long id) {
		if (categoryRepo.existsById(id)) {
			categoryRepo.deleteById(id);
			return true;
		}
		return false;
	}
}
