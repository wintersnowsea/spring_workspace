package com.edu.springshop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springshop.admin.domain.Pimg;
import com.edu.springshop.admin.domain.Product;
import com.edu.springshop.exception.PimgException;
import com.edu.springshop.exception.ProductException;
import com.edu.springshop.exception.UploadException;
import com.edu.springshop.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService {

	//dao 모델
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private PimgDAO pimgDAO;
	
	//파일매니저 모델
	@Autowired
	private FileManager fileManager;
	

	@Override
	public List selectAll() {
		return productDAO.selectAll();
	}

	@Override
	public Product select(int product_idx) {
		return productDAO.select(product_idx);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Product product, String dir) throws ProductException, UploadException, PimgException {
		// 상품저장(부모  product)
		productDAO.insert(product); 		//select-key 에 의해 fk 존재

		//파일저장
		fileManager.save(product, dir); 		// fk가 있는 product
		
		//이미지 저장(Pimg)
		List<Pimg> pimgList = product.getPimgList();
		
		for(Pimg pimg : pimgList) {

			 pimgDAO.insert(pimg);
		 }
		 
	}

	@Override
	public void update(Product product) {
		productDAO.update(product);
	}

	@Override
	public void delete(int product_idx) {
		productDAO.delete(product_idx);
	}
	

}