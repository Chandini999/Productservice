package com.ProductService.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProductService.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findBySelleridAndProdid(Integer sellerid,Integer prodid);
	
	Optional<Product> findByProdnameAndCategory(String prodname,String category);
	
	Optional<Product> deleteByProdid(Integer prodid);

	Product findByProdidAndSellerid(Integer prodid, Integer sellerid);

	List<Product> findAllByCategory(String category);

	List<Product> findAllByprodname(String prodname);


}
