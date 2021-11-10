package com.ProductService.entity;

//import javax.persistence.Column;
//import javax.persistence.Table;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
//@Table(name="Product")
public class Product {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prodid",unique=true)
	Integer prodid;
	@Column(name = "prodname")
	
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*", message="{product.name.invalid}")
	String prodname;
	
	@Column(name="price")
	@Min(value=200)	
	Integer price;
	
	Integer stock;
	
	@Size(min=10,max=500,message="{description.invalid}")
	String description;
	Integer sellerid;
//	Integer Buyerid;
	
	@Size(min=10,max=500,message="{category.invalid}")
	String category;
	
	@Size(min=10,max=500,message="{subcategorycategory.invalid}")
	String subcategory;
	Integer productrating;

	
	
	
	
	public Integer getProdid() {
		return prodid;
	}




	public void setProdid(Integer prodid) {
		this.prodid = prodid;
	}




	public String getProdname() {
		return prodname;
	}




	public void setProdname(String prodname) {
		this.prodname = prodname;
	}




	public Integer getPrice() {
		return price;
	}




	public void setPrice(Integer price) {
		this.price = price;
	}

    


	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Integer getStock() {
		return stock;
	}




	public void setStock(Integer stock) {
		this.stock = stock;
	}




	public Integer getSellerid() {
		return sellerid;
	}

	public void setSellerid(Integer sellerid) {
		this.sellerid = sellerid;
	}

//
//
//
//	public Integer getBuyerid() {
//		return Buyerid;
//	}
//
//
//
//
//	public void setBuyerid(Integer buyerid) {
//		this.Buyerid = buyerid;
//	}




	public String getCategory() {
		return category;
	}




	public void setCategory(String category) {
		this.category = category;
	}




	public String getSubcategory() {
		return subcategory;
	}




	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}




	public Integer getProductrating() {
		return productrating;
	}




	public void setProductrating(Integer productrating) {
		this.productrating = productrating;
	}
	

	public Product() {
		super();
	}
}
