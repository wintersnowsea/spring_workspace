package com.edu.springshop.model.admin;

import com.edu.springshop.admin.domain.Admin;

public interface AdminService {
	public void insert(Admin admin);
	public Admin select(Admin admin);
}
