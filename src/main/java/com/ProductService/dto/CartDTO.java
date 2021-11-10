package com.ProductService.dto;

import java.util.Objects;

public class CartDTO {

//	Integer buyerid;
//	Integer Prodid;
	Integer Sellerid;
	Integer quantity;
	
//    public Integer getBuyerid() {
//		return buyerid;
//	}
//	public void setBuyerid(Integer buyerid) {
//		this.buyerid = buyerid;
//	}
//	
//    public Integer getProdid() {
//		return Prodid;
//	}
//	public void setProdid(Integer prodid) {
//		Prodid = prodid;
//	}
	public Integer getQuantity() {
		return quantity;
	}
	public Integer getSellerid() {
		return Sellerid;
	}
	public void setSellerid(Integer sellerid) {
		Sellerid = sellerid;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public int hashCode() {
		return Objects.hash( Sellerid,quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDTO other = (CartDTO) obj;
		return Objects.equals(Sellerid, other.Sellerid)
				 && Objects.equals(quantity, other.quantity);
	}
		
	

	
}
