package com.Oran.Makizone;

import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.CategoryService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.ProductSKUService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EntityScan("com.Oran.Makizone.Database.HibernateJPA.Model")
public class MakizoneApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MakizoneApplication.class, args);

		CategoryService categoryservice = context.getBean(CategoryService.class);
		ProductService productService = context.getBean(ProductService.class);
		ProductSKUService productSKUService = context.getBean(ProductSKUService.class);

	}

}
