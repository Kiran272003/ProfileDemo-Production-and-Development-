package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.*;
import com.example.demo.Service.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    // Single controller endpoint: Get products for a customer
    @GetMapping("/customer/{customerName}/products")
    public List<Product> getCustomerProducts(@PathVariable("customerName") String customerName) {
        return orderService.getProductsOrderedByCustomer(customerName);
    }
}
