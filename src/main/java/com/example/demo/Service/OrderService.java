package com.example.demo.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.Product;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getProductsOrderedByCustomer(String customerName) {
        return orderRepository.findProductsOrderedByCustomer(customerName);
    }
}
