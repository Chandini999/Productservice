package com.ProductService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.ProductService.entity.Product;
import com.ProductService.entity.subscribedproduct;

public interface subscribeRepository extends JpaRepository<subscribedproduct, Integer> {
	
	

}
