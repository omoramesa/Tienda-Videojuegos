package com.tienda.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tienda.api.models.Product;

@Repository
@Transactional
public class ProductDaoImp  extends AbstractSession implements ProductDao{

	public ProductDaoImp() {
	}
	
	@Override
	public void saveProduct(Product product) {
		getSession().persist(product);
	}

	@Override
	public void updateProduct(Product product) {
		getSession().update(product);
	}

	@Override
	public Product findById(Long id) {
		return getSession().get(Product.class, id);
	}

	@Override
	public Product findByName(String titulo) {
		return (Product) getSession().createQuery(
				" from Product where titulo = :titulo")
				.setParameter("titulo", titulo)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByDirector(String director) {
		return getSession().createQuery(
				" from Product where director = :director")
				.setParameter("director", director).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProtagonista(String protagonista) {
		return getSession().createQuery(
				" from Product where protagonista = :protagonista")
				.setParameter("protagonista", protagonista).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProductor(String productor) {
		return getSession().createQuery(
				" from Product where productor = :productor")
				.setParameter("productor", productor).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByMarca(String marca) {
		return getSession().createQuery(
				" from Product where marca = :marca")
				.setParameter("marca", marca).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAllProducts() {
		return getSession().createQuery("from Product").list();
	}

	@Override
	public boolean isProductExist(Product product) {
		Product productResponse = findByName(product.getTitulo());
		boolean vBalid = false;
		
		if(productResponse !=null) {
			if(product.getIde_producto() != productResponse.getIde_producto()) {
				vBalid = true;
			}
		}
		return vBalid;
	}

}
