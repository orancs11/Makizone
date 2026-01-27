package com.Oran.Makizone.Service.Catalog;


import com.Oran.Makizone.Model.Catalog.ProductSKU;
import com.Oran.Makizone.Repository.Catalog.ProductSKURepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductSKUService {

    private ProductSKURepo repo;

    @Autowired
    public ProductSKUService(ProductSKURepo repo){
        this.repo = repo;
    }

    public ProductSKU getByName(String name){
        Optional<ProductSKU> list = this.repo.findByName(name);
        ProductSKU result = new ProductSKU();
        if(list.isPresent()){
            result = list.get();
        }
        return result;
    }
}
