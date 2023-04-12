package com.edu.springshop.model.member;

import java.util.List;
import com.edu.springshop.domain.SnS;

public interface SnSDAO {
	public List selectAll();
	public SnS selectByIdx(int sns_idx);
	public SnS selectByName(String sns_name);
}
