package com.ProductService.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ProductService.dto.CartDTO;
import com.ProductService.dto.ProductDTO;
import com.ProductService.dto.WishListDTO;
//import com.ProductService.entity.Cart;
import com.ProductService.entity.Product;
//import com.ProductService.entity.WishList;
import com.ProductService.service.ProductService;
import com.ProductService.service.subscribeservice;
//import com.ProductService.service.WishListService;
//import com.infy.UserMS.entity.Cart;

@RestController
@CrossOrigin
public class ProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService prodService;
	
	@Autowired
	subscribeservice subservice;
	
//	@Autowired
//	WishListService wishlistservice;
	@GetMapping(value = "Product/{prodid}/{sellerid}")
	public ResponseEntity<Product> searchProductByids(@PathVariable Integer prodid,@PathVariable Integer sellerid) {
		Product prod = prodService.searchProductByids(prodid,sellerid);
		System.out.println("product search found");
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

	@PutMapping(value = "product/{sellerid}/{prodid}/changestock/{stock}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Product> updatestock(@PathVariable Integer sellerid,@PathVariable Integer prodid,@PathVariable Integer stock) {
		Product productlist = prodService.updatestock(prodid,sellerid,stock);
		System.out.println("status update");
		return new ResponseEntity<>(productlist, HttpStatus.OK);
	}
	
//    
	// Fetches full profile of a specific customer
	
	//adding products
	@PostMapping(value = "Product/addproduct/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO prodDTO) {
		Product prod = prodService.addProduct(prodDTO);
		return new ResponseEntity<>(prod, HttpStatus.OK);
		
	}
	
	//searching products
	@GetMapping(value = "Product/{prodname}/searchproduct/{category}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> searchProduct(@PathVariable String prodname,@PathVariable String category,@RequestBody ProductDTO prodDTO) {
		Product prod = prodService.searchProduct(prodname,category,prodDTO);
		System.out.println("product search found");
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	//deletion from product
	@DeleteMapping(value= "product/removeproduct/{prodid}")
	public void removeProduct(@PathVariable Integer prodid) {
		prodService.removeProduct(prodid);
	}
	
	//product updates
   @PutMapping(value = "{sellerid}/product/{prodid}/updatestatus", consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Product> updatestatus(@PathVariable Integer sellerid,@PathVariable Integer prodid,@RequestBody Integer stock) {
		Product productlist = prodService.updatestatus(prodid,sellerid,stock);
		System.out.println("status update");
		return new ResponseEntity<>(productlist, HttpStatus.OK);
		
	}
   
   //wishlist adding
   @PostMapping(value = "wishlist/addproduct",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void  addProduct(@RequestBody WishListDTO wishDTO) {
		
		String url = "http://localhost:9000/wishlist/{prodid}/{buyerid}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, WishListDTO.class,wishDTO.getProdid(),wishDTO.getBuyerid());
		//WishListDTO wish = wishlistservice.addProduct(prodid,buyerid,wishDTO);
      
		//String successMessage = Environment.getProdid("Order_SUCCESS");
       //System.out.println("product added sucessfully to wishlist");
		//return new ResponseEntity<>(wi, HttpStatus.OK);
		
		
	}
   
   //deletion from wishlist
	
	@DeleteMapping(value= "{buyerid}/removeproduct/wishlist/{prodid}")
	public void removeProduct(@PathVariable Integer buyerid, @PathVariable Integer prodid) {
		String url = "http://localhost:9000/{buyerid}/wishlist/removeproduct/{prodid}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url,buyerid,prodid);

	}

	//moving from wishlist
	
	@PostMapping(value = "{buyerid}/moveproduct/wishlist/{prodid}/cart", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void moveproductProduct(@PathVariable Integer buyerid,@PathVariable Integer prodid,@RequestBody CartDTO cartdto ) {
		String url = "http://localhost:9000/{buyerid}/wishlist/moveproduct/{prodid}/cart/{sellerid}/{quantity}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, CartDTO.class,buyerid,prodid,cartdto.getSellerid(),cartdto.getQuantity());
		//CartDTO cart =WishListDTO.moveProduct(buyerid,prodid, cartdto);		
		//String successMessage = Environment.getProdid("Order_SUCCESS");
        //System.out.println("product added sucessfully from wishlist to cart");
		//return new ResponseEntity<>(cart, HttpStatus.OK);
		
		
	}
	
	@GetMapping(value="/products/ByName/{prodname}")
	public List<Product> getProductByName(@PathVariable String prodname){
		List<Product> p=prodService.getProductByName(prodname);
		return p;
	}
	
	@GetMapping(value="/products/ByCategory/{category}")
	public List<Product> getProductByCategory(@PathVariable String category){
		List<Product> p=prodService.getProductByCategory(category);
		return p;
	}
	@PostMapping(value="{buyerid}/subscibed/{prodid}")
	public void subscribeproduct(@PathVariable Integer buyerid,@PathVariable Integer prodid) {
		
		String url = "http://localhost:9000/subscribe/{buyerid}";
		RestTemplate restTemplate = new RestTemplate();
		Boolean b = restTemplate.getForObject(url,Boolean.class,buyerid);
		if(b) {
			subservice.subscribetoaddproduct(buyerid,prodid);
		}

		
		
	}





}
