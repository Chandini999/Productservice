package com.ProductService.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

//import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProductService.dto.ProductDTO;
import com.ProductService.entity.Product;
import com.ProductService.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository prodrepo;

	public void removeProduct(Integer prodid) {
		prodrepo.deleteByProdid(prodid);
		
	}

	public Product addProduct(ProductDTO prodDTO) {
		Product  prod = new Product();
		prod.setProdid(prodDTO.getProdid());
		prod.setProdname(prodDTO.getProdname());
		prod.setPrice(prodDTO.getPrice());
		prod.setStock(prodDTO.getStock());
		prod.setDescription(prodDTO.getDescription());
		prod.setSellerid(prodDTO.getSellerid());
//		prod.setBuyerid(prodDTO.getBuyerid());
		prod.setCategory(prodDTO.getCategory());
		prod.setSubcategory(prodDTO.getSubcategory());
		prod.setProductrating(prodDTO.getProductrating());
	    prodrepo.save(prod);
		return prod;
	}
	public List<Product> getProductByCategory(String category){
		List<Product> p=prodrepo.findAllByCategory(category);
		return p;
	}
	
	public List<Product> getProductByName(String prodname){
		List<Product> p=prodrepo.findAllByprodname(prodname);
		return p;
	}
	public Product searchProduct(String prodname, String category,ProductDTO productdto) {
		Optional<Product> optimal = prodrepo.findByProdnameAndCategory(prodname,category);
		if(optimal.isPresent()) {
			Product p=optimal.get();
			p.getProdname();
			p.getCategory();
			return p;
		}
		return null;
	}
	
	public Product searchProductByids(Integer prodid, Integer sellerid) {		
		Product prod= prodrepo.findByProdidAndSellerid(prodid,sellerid);
		return prod;
}
	
	public Product updatestatus(Integer prodid,Integer sellerid, Integer stock) {
		Optional<Product> optimal = prodrepo.findBySelleridAndProdid(sellerid,prodid);
		if(optimal.isPresent()) {
			Product p= optimal.get();
			p.setStock(stock);
			prodrepo.save(p);
		return p;
	}
		return null;
	}

	public Product updatestock(Integer prodid,Integer sellerid, Integer stock) {
		Optional<Product> optimal = prodrepo.findBySelleridAndProdid(sellerid,prodid);
		if(optimal.isPresent()) {
			Product p= optimal.get();
			p.setStock(stock);
			prodrepo.save(p);
		return p;
		}
		// TODO Auto-generated method stub
		return null;
	}


}
