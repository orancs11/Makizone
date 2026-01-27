package com.Oran.Makizone.Repository;

import com.Oran.Makizone.Model.ProductSKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSKURepo extends JpaRepository<ProductSKU, Long> {

    public Optional<ProductSKU> findByName(String name);
}
