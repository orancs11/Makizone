package com.Oran.Makizone.Repository;


import com.Oran.Makizone.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository {

    public Optional<Category> findBySlug(String slug);
}
