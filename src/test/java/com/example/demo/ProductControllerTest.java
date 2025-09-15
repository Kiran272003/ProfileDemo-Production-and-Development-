package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Repository.Product;
import com.example.demo.Service.ProductService;


import com.example.demo.Controller.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.ProductService;
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Product product;
    
    @MockitoBean
    private ProductService productService;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setId(1L);
        product.setName("laptop");
        product.setPrice(100.00);
    }

    @Test
    @DisplayName("GET -- /products endpoint")
    public void getProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>(List.of(product)));

        mockMvc.perform(get("/products"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("laptop"));
    }

    @Test
    @DisplayName("GET -- /products/{id} endpoint")
    public void getProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("laptop"))
               .andExpect(jsonPath("$.price").value(100.0));
    }

    @Test
    @DisplayName("POST -- /products endpoint")
    public void addProduct() throws Exception {
        var myProd = new Product(3L, "Mobile", 300.00);

        String jsonRequest = """
            {
              "name": "Mobile",
              "price": 300.0
            }
        """;

        when(productService.addProduct(any(Product.class))).thenReturn(myProd);

        mockMvc.perform(post("/products")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(jsonRequest))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("Mobile"));
//               .andExpect(jsonPath("$.price").value(300.0));
    }
    
    
    
    @Test
    @DisplayName("GET -- /filter endpoint")
    public void filterByPrice() throws Exception {
    	 when(productService.getProductByPriceLessThan(300.00))
         .thenReturn(List.of(product));

   
     mockMvc.perform(get("/products/filter")
           .param("price", "300.00"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].name").value("laptop"))
         .andExpect(jsonPath("$[0].price").value(100.0));
    }
    
    
    @Test
    @DisplayName("get -- /filterbyname endpont")
    public void filterbyname() throws Exception {
    	when(productService.filterByName("laptop"))
    	.thenReturn(List.of(product));
    	
    	mockMvc.perform(get("/products/filtername")
    	.param("name","laptop"))
.andExpect(status().isOk())
.andExpect(jsonPath("$[0].name").value("laptop"))
.andExpect(jsonPath("$[0].price").value(100));


    	
    }
    
    
    
}
