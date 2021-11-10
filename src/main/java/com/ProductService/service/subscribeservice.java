package com.ProductService.service;

import com.ProductService.entity.subscribedproduct;
import com.ProductService.repository.subscribeRepository;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class subscribeservice {
	
	@Autowired
	subscribeRepository subrepo;
 
	public void subscribetoaddproduct(Integer buyerid,Integer prodid) {
	
	   subscribedproduct sub=new subscribedproduct();
	   sub.setBuyerid(buyerid);
	   sub.setProdid(prodid);
	   subrepo.save(sub);
		
	
	}
}
