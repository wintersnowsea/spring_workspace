package com.edu.springshop.model.product;

import java.util.List;

import com.edu.springshop.admin.domain.Pimg;
import com.edu.springshop.admin.domain.Product;

public interface PimgDAO {

	public List selectAll();
	public List selectByProduct(int product_idx);
	public void insert(Pimg pimg);
	public void delete(int product_idx);
	
	
}
