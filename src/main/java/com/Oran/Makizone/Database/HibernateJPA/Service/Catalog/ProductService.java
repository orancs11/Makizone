package com.Oran.Makizone.Database.HibernateJPA.Service.Catalog;

import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.Category;
import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.Product;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private ProductRepo repo;

    @Autowired
    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public void createProduct(String name, String scientificName, String description, Category category) {
        Product tempProduct = new Product();
        tempProduct.setName(name);
        tempProduct.setScientificName(scientificName);
        tempProduct.setDescription(description);
        tempProduct.setCategory(category);
        this.repo.save(tempProduct);
    }

    public String categoryNumber(String name) {
        Product currProduct = getProduct(name);
        if (currProduct == null) {
            throw new RuntimeException("Product not found with name: " + name);
        }
        Category currCategory = currProduct.getCategory();
        if (currCategory == null) {
            throw new RuntimeException("Product " + name + " has no category assigned.");
        }
        return String.valueOf(currCategory.getId());
    }

    private Product getProduct(String name) {
        Optional<Product> currProduct = this.repo.findByName(name);
        if (currProduct.isEmpty())
            return null;
        return currProduct.get();
    }
}
