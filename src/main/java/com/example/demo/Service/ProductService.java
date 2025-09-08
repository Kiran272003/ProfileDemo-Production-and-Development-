package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.Product;
import com.example.demo.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);

	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);

	}

	public Product addProduct(Product prod) {
		return productRepository.save(prod);
	}

	public List<Product> getProductByPriceLessThan(Double price) {
		return productRepository.filterByPrice(price);
	}

	public List<Product> filterByName(String name) {
		return productRepository.filterByName(name);
	}

}
