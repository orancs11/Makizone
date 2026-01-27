package com.Oran.Makizone;

import com.Oran.Makizone.Model.Category;
import com.Oran.Makizone.Service.CategoryService;
import com.Oran.Makizone.Service.ProductService;
import org.hibernate.SessionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MakizoneApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MakizoneApplication.class, args);
		CategoryService categoryservice = context.getBean(CategoryService.class);
		ProductService productService = context.getBean(ProductService.class);
	}

}
