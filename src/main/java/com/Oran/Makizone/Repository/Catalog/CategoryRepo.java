package com.Oran.Makizone.Repository.Catalog;


import com.Oran.Makizone.Model.Catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    public Optional<Category> findBySlug(String slug);
    public Optional<Category> findById(int id);
}
