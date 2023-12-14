package com.apapedia.catalogue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apapedia.catalogue.model.Category;
import com.apapedia.catalogue.service.CategoryService;

import jakarta.transaction.Transactional;

import java.util.List;

@SpringBootApplication
public class CatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(CategoryService categoryService) {
		return args -> {
			List<Category> categories = categoryService.getAllCategory();
			List<String> categoryNames = List.of(
					"Aksesoris Fashion",
					"Buku & Alat Tulis",
					"Elektronik",
					"Fashion Bayi & Anak",
					"Fashion Muslim",
					"Fotografi",
					"Hobi & Koleksi",
					"Jam Tangan",
					"Perawatan & Kecantikan",
					"Makanan & Minuman",
					"Otomotif",
					"Perlengkapan Rumah",
					"Souvenir & Party Supplies"
			);
			if (categories.isEmpty()) {
				for (String categoryName : categoryNames) {
					Category category = new Category();
					category.setName(categoryName);
					categoryService.save(category);
				}
			}

	};

}
}
