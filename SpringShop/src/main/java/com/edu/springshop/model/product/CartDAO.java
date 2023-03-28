package com.edu.springshop.model.product;

import java.util.List;

import com.edu.springshop.admin.domain.Cart;
import com.edu.springshop.admin.domain.Member;

public interface CartDAO {
	public List selectAll(Member member);
	public void insert(Cart cart);
	public int selectCount(Cart cart);
	public void updateEa(Cart cart);
}
