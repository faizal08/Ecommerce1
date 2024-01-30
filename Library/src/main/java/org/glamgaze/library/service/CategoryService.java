package org.glamgaze.library.service;

import org.glamgaze.library.dto.CategoryDto;
import org.glamgaze.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService
{
    Category save(CategoryDto categoryDto);
    List<Category> findAll();
    Category update(Category category);
    Optional<Category> findById(Long id);
    void deleteById(Long id);
    void enableById(Long id);
    List<Category> findAllByActivatedTrue();
    Long countAllCategories();
    Category findById(long id);
    void disableCategoryAndProductsById(Long id);
}
