package com.tienda.api.services;

import java.util.List;

import com.tienda.api.models.Product;

public interface ProductService {

	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	Product findById(long id);
	
	Product findByName(String titulo);
	
	List<Product> findByDirector(String director);
	
	List<Product> findByProtagonista(String protagonista);
	
	List<Product> findByProductor(String productor);
	
	List<Product> findByMarca(String marca);
	
	List<Product> findAllProducts();
	
	boolean isProductExist(Product product);
}
