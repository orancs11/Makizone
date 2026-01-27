package com.Oran.Makizone.Service;


import com.Oran.Makizone.Model.Category;
import com.Oran.Makizone.Repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepo repo;
    @Autowired
    public CategoryService(CategoryRepo repo){
        this.repo = repo;
    }

    @Transactional
    public void createCategory(String name, String slug, String desc){
        Category category = new Category();
        category.setName(name);
        category.setSlug(slug);
        category.setDescription(desc);
        repo.save(category);
    }

    @Transactional
    public void addCategory(Category category){
        repo.save(category);
    }

    @Transactional
    public void addCategories(List<Category> categoryList){
        repo.saveAll(categoryList);
    }
}
