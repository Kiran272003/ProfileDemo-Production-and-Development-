package com.example.demo.test_controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Repository.Product;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductController {

	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("integration test post get operation with REST API")
	public void createAndGetProductTest()
	{
		Product product = new Product();
		product.setName("Laptop");
		product.setPrice(50.0);
		
		ResponseEntity<Product> postResponse = restTemplate.postForEntity("/products", product, Product.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		Product created = postResponse.getBody();
		assertThat(created).isNotNull();
		assertThat(created.getName()).isEqualTo("Laptop");
		
		ResponseEntity<Product> getResponseEntity = restTemplate.getForEntity("/products/" + created.getId(), Product.class);
		assertThat(getResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponseEntity.getBody().getName()).isEqualTo("Laptop");
	}
}
