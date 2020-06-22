package com.tienda.api.dao;

import java.util.List;

import com.tienda.api.models.Order;
import com.tienda.api.models.CreateOrder;

public interface OrderDao {
	void saveOrder( CreateOrder order);
	
	Order findById( Long id);
	
	List<Order> findAllOrders();
	
	List<Order> findByIdeIdentificacion(String identificacion);
	
	List<Order> findByFecha(String fecha_alquiler);
	
	Order findByIdeClient(Long id);
}
