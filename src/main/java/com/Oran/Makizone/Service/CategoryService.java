package com.Oran.Makizone.Service;


import com.Oran.Makizone.Model.Category;
import com.Oran.Makizone.Repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo repo;

    @Transactional
    public void createCategory(String name, String slug, String desc){
        Category category = new Category();
        category.setName(name);
        category.setSlug(slug);
        category.setDescription(desc);
        repo.save(category);
    }

    public Category getCategory(int id){
        Optional<Category> temp = this.repo.findById(id);
        Category result = new Category();
        if(temp.isPresent()){
            result = temp.get();
        }
        return result;
    }

    @Transactional
    public void addCategory(Category category){
        repo.save(category);
    }

    @Transactional
    public void addCategories(List<Category> categoryList){
        repo.saveAll(categoryList);
    }

    @Transactional
    public void removeCategory(Category category){
        this.repo.delete(category);
    }

    @Transactional
    public void removeCategory(Long id){
        this.repo.deleteById(id);
    }

}
