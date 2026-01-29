package com.Oran.Makizone;

import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.CategoryService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.ProductSKUService;
import com.Oran.Makizone.Database.HibernateJPA.Service.Catalog.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MakizoneApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MakizoneApplication.class, args);

	}

}
