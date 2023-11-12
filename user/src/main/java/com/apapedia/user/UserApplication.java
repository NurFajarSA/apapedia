package com.apapedia.user;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apapedia.user.dto.request.SignUpUserRequestDTO;
import com.apapedia.user.model.Seller;
import com.apapedia.user.service.UserService;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(UserService userService) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// create 10 users seller
			for (int i = 0; i < 10; i++) {
				var userSeller = new SignUpUserRequestDTO();
				userSeller.setName(faker.name().fullName());
				userSeller.setUsername(faker.name().username());
				userSeller.setPassword("12345678");
				userSeller.setEmail(faker.internet().emailAddress());
				userSeller.setAddress(faker.address().fullAddress());
				userSeller.setCategory(faker.company().industry());
				userService.signUp(userSeller);
			}
		};

	}
}
