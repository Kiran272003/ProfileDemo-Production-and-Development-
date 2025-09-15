package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Repository.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    Product prod1;
    Product prod2;

    @BeforeEach
    public void setup() {
        prod1 = new Product();
        prod1.setId(1L);
        prod1.setName("Laptop");
        prod1.setPrice(100.0);

        prod2 = new Product();
        prod2.setId(2L);
        prod2.setName("desktop");
        prod2.setPrice(200.0);
    }

    @Test
    @DisplayName("Test getting all products")
    public void getProducts() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>(List.of(prod1, prod2)));

        List<Product> productList = productService.getAllProducts();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);
        assertThat(productList.get(0).getName()).isEqualTo("Laptop");
        assertThat(productList.get(0).getPrice()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("Test getting product by ID")
    public void findProductById() {
        when(productRepository.findById(2L)).thenReturn(Optional.of(prod2));

        var foundProduct = productService.getProductById(2L);

        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("desktop");
        assertThat(foundProduct.get().getPrice()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Test adding a product")
    public void addProduct() {
        Product myNewProduct = new Product();
        myNewProduct.setName("desktop");
        myNewProduct.setPrice(400.00);

        when(productRepository.save(myNewProduct)).thenReturn(myNewProduct);

        var savedProduct = productService.addProduct(myNewProduct);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo("desktop");
        assertThat(savedProduct.getPrice()).isEqualTo(400.00);
        verify(productRepository, times(1)).save(savedProduct);
    }

    @Test
    @DisplayName("Test deleting a product")
    public void deleteProduct() {
        Long deleteId = 1L;

        // Call the method
        productService.deleteProduct(deleteId);

        // Verify that repository method was called once
        verify(productRepository, times(1)).deleteById(deleteId); 
    }
}
