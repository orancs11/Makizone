package com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog;

import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.ProductSKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSKURepo extends JpaRepository<ProductSKU, Long> {

    public Optional<ProductSKU> findByName(String name);
}
