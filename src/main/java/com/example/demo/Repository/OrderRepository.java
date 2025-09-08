package com.example.demo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    
	  @Query("SELECT o.product FROM Orders o WHERE o.customerName = :customerName")
	    List<Product> findProductsOrderedByCustomer(@Param("customerName") String customerName);
}
