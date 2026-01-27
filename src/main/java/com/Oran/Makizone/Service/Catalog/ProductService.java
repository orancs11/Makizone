package com.Oran.Makizone.Service.Catalog;


import com.Oran.Makizone.Model.Catalog.Category;
import com.Oran.Makizone.Model.Catalog.Product;
import com.Oran.Makizone.Repository.Catalog.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepo repo;
    @Autowired
    public ProductService(ProductRepo repo){
        this.repo = repo;
    }

    @Transactional
    public void createProduct(String name, String scientificName, String description, Category category){
        Product tempProduct = new Product();
        tempProduct.setName(name);
        tempProduct.setScientificName(scientificName);
        tempProduct.setDescription(description);
        tempProduct.setCategory(category);
        this.repo.save(tempProduct);
    }
}
