package com.edu.springshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.springshop.admin.domain.Cart;
import com.edu.springshop.admin.domain.Member;
import com.edu.springshop.exception.CartException;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public List selectAll(Member member) {
		return cartDAO.selectAll(member);
	}

	@Override
	public void insert(Cart cart) throws CartException {
		cartDAO.insert(cart);
	}

	@Override
	public int selectCount(Cart cart) {
		return 0;
	}

	@Override
	public void updateEa(Cart cart) {
		
	}

}
