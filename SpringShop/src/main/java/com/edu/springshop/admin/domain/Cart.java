package com.edu.springshop.admin.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_idx;
	private Member member;
	private Product product;
	private int ea;
}
