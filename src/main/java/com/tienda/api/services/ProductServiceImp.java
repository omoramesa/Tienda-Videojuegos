package com.tienda.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.api.dao.ProductDao;
import com.tienda.api.models.Product;

@Service("productService")
@Transactional
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Override
	public Product findById(long id) {
		return productDao.findById(id);
	}

	@Override
	public Product findByName(String titulo) {
		return productDao.findByName(titulo);
	}

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAllProducts();
	}

	@Override
	public boolean isProductExist(Product product) {
		return productDao.isProductExist(product);
	}

	@Override
	public List<Product> findByDirector(String director) {
		return productDao.findByDirector(director);
	}
	
	@Override
	public List<Product> findByProtagonista(String protagonista) {
		return productDao.findByProtagonista(protagonista);
	}
	
	@Override
	public List<Product> findByProductor(String productor) {
		return productDao.findByProductor(productor);
	}
	
	@Override
	public List<Product> findByMarca(String marca) {
		return productDao.findByMarca(marca);
	}

}
