package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.Product;
import com.example.demo.Service.ProductService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
@Tag(name="Product Management" , description = "product endpoints",
externalDocs= @ExternalDocumentation(url="https://google.com",description="MORE DETAIL INFO"))
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	
	@GetMapping
	@Operation(summary=" Listing of all the producst")
	
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Received data from server",
					content=@Content(mediaType="application/json",
					schema=@Schema(implementation=Product.class,description ="expecting this kind of output"),
			   examples = @ExampleObject(value="{\"id\" : 101,\"name\" : \"laptop\",\"price\":300}",description="expecting this kinf of o/p"))),
			@ApiResponse(responseCode = "500", description="there is a internal server error"),
			@ApiResponse(responseCode="400",description="NO Product found")
	})
	public List<Product> getProducts()
	{
		return productService.getAllProducts();
		
	}
	
//	@RequestMapping("/{proId}")
	@GetMapping("/{proId}")
	@Operation(summary=" Get product by ID")
	
	public Optional<Product> getProductById(
	@Parameter(description="need product ID",required = true)
	@PathVariable("proId") long proId)
	{
		
		return productService.getProductById(proId);
		
	}
	@PostMapping
	@Operation(summary=" Adding a product")
	
	public ResponseEntity<Product> addProduct(@RequestBody Product pro)
	{
		Product savedProduct = productService.addProduct(pro);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedProduct);
		
		
	}
	
	
	@GetMapping("/filter")
	@Operation(summary=" Filter by the price")
	public List<Product> getProductByPriceLessThan(@RequestParam("price") Double price)
	{
		return productService.getProductByPriceLessThan(price);
	}
	
	
	@GetMapping("/filtername")
	@Operation(summary=" Filtering by the name ")
	public List<Product> getProductByName(@RequestParam("name") String name)
	{
		return productService.filterByName(name);
	}
}
