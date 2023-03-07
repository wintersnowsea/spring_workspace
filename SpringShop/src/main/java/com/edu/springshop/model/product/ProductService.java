package com.edu.springshop.model.product;

import java.util.List;

import com.edu.springshop.admin.domain.Product;

public interface ProductService {
	
	public List selectAll();
	public Product select(int product_idx);
	public void regist(Product product, String dir); 		//디비 연동+file제어도 같이하니까.  //회원가입의 경우 + 메일발송 ....ㄷ등등
	public void update(Product product);
	public void delete(int product_idx);

}