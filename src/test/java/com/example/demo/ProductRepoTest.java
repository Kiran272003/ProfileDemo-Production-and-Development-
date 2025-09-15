package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.Repository.Product;
import com.example.demo.Repository.ProductRepository;

@DataJpaTest

//@ActiveProfiles("mysql - test")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("Integration test save product")
	public void saveProduct() {

		Product product = new Product();

		product.setName("test product");
		product.setPrice(3000);

		Product saved = productRepository.save(product);
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isNotNull();
	}

	@Test
	@DisplayName("Integration test fidn product by ID")
	public void testFindById() {
		Product product = new Product();
		product.setName("find the product");
		product.setPrice(20);
		Product saved = entityManager.persistAndFlush(product);

		Optional<Product> found = productRepository.findById(saved.getId());
		assertThat(found).isPresent();

		assertThat(found.get().getName()).isEqualTo("find the product");
	}
	
	@Test 
	@DisplayName("Integration-Test custom filter by Price method ")
	public void testFilterByPrice()
	{
		Product product = new Product();
		
		product.setName("less cost product");
		product.setPrice(200);
		
		entityManager.persistAndFlush(product);
		
		Product expensive = new Product();
		expensive.setName("expensive product");
		expensive.setPrice(500);
		
		entityManager.persistAndFlush(expensive);
		
		
		List<Product> filtered = productRepository.filterByPrice(250.0);
		assertThat(filtered).hasSize(1);
		assertThat(filtered.get(0).getPrice()).isLessThan(250.0);
		
		
	}
	
	@Test
	@DisplayName("Delete the product")
	
	public void deleteProduct()
	{
		Product product = new Product();
		
		product.setName("delete");
		
		product.setPrice(3000.0);
		Product saved = entityManager.persistAndFlush(product);
		
		productRepository.delete(product);
		
		Optional<Product> found = productRepository.findById(saved.getId());
		
	assertThat(found).isEmpty();
	}
	
	
}
