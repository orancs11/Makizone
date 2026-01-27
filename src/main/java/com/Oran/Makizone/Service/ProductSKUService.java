package com.Oran.Makizone.Service;


import com.Oran.Makizone.Model.Product;
import com.Oran.Makizone.Model.ProductSKU;
import com.Oran.Makizone.Repository.ProductSKURepo;
import org.aspectj.apache.bcel.classfile.Module;
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
