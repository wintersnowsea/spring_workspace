package com.edu.springshop.model.product;

import java.util.List;

import com.edu.springshop.domain.Cart;
import com.edu.springshop.domain.Member;

public interface CartService {
	public List selectAll(Member member);
	public void insert(Cart cart);
	
}
