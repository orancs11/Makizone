package com.Oran.Makizone.Config;

import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.Product;
import com.Oran.Makizone.Database.HibernateJPA.Model.Catalog.ProductSKU;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog.ProductRepo;
import com.Oran.Makizone.Database.HibernateJPA.Repository.Catalog.ProductSKURepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepo productRepo;
    private final ProductSKURepo skuRepo;

    public DataInitializer(ProductRepo productRepo, ProductSKURepo skuRepo) {
        this.productRepo = productRepo;
        this.skuRepo = skuRepo;
    }

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DataInitializer.class);

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("Cleaning up database...");
        productRepo.findByName("tempName").ifPresent(p -> {
            logger.info("Removing temporary product: {}", p.getName());
            // Remove SKUs first to handle foreign key
            List<ProductSKU> skus = skuRepo.findByProduct(p);
            skuRepo.deleteAll(skus);
            productRepo.delete(p);
        });

        logger.info("Checking for products without SKUs...");
        List<Product> products = productRepo.findAll();
        for (Product product : products) {
            List<ProductSKU> skus = skuRepo.findByProduct(product);
            if (skus.isEmpty()) {
                logger.info("Creating default SKU for product: {}", product.getName());
                ProductSKU sku = new ProductSKU();
                sku.setName(product.getName() + " - Standard");
                sku.setProduct(product);
                sku.setPrice(BigDecimal.ZERO); // Free
                sku.setStock_quantity(100);
                sku.setActive(true);
                sku.setSkuCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                skuRepo.save(sku);
            }
        }
    }
}
