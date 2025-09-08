package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.Product;
import com.example.demo.Service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	
	@GetMapping
	public List<Product> getProducts()
	{
		return productService.getAllProducts();
		
	}
	
	@RequestMapping("/{proId}")
	public Optional<Product> getProductById(@PathParam("proId") Long pid)
	{
		return productService.getProductById(pid);
		
	}
	@PostMapping
	
	public ResponseEntity<Product> addProduct(@RequestBody Product pro)
	{
		Product savedProduct = productService.addProduct(pro);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedProduct);
		
		
	}
	
	
	@GetMapping("/filter")
	public List<Product> getProductByPriceLessThan(@RequestParam("price") Double price)
	{
		return productService.getProductByPriceLessThan(price);
	}
	
	
	@GetMapping("/filtername")
	public List<Product> getProductByName(@RequestParam("name") String name)
	{
		return productService.filterByName(name);
	}
}
