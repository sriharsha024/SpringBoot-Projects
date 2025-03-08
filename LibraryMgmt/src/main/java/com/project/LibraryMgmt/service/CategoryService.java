package com.project.LibraryMgmt.service;

import com.project.LibraryMgmt.entity.Category;
import com.project.LibraryMgmt.exception.ResourceNotFoundException;
import com.project.LibraryMgmt.payload.CategoryDTO;
import com.project.LibraryMgmt.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOS=categories.stream().map(c->modelMapper.map(c, CategoryDTO.class)).collect(Collectors.toList());
        return categoryDTOS;
    }

    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
       return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        category = categoryRepo.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        Category categoryFromDB = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryFromDB.setCategoryName(categoryDTO.getCategoryName());
        Category categoryUpdated = categoryRepo.save(categoryFromDB);
        return modelMapper.map(categoryUpdated, CategoryDTO.class);
    }

    public boolean deleteCategory(long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepo.delete(category);
        return true;
    }
}
