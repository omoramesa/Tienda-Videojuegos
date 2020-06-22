package com.tienda.api.services;

import java.util.List;

import com.tienda.api.models.Order;
import com.tienda.api.models.CreateOrder;

public interface OrderService {
	
	void saveOrder( CreateOrder order);
	
	Order findById( Long id);
	
	List<Order> findAllOrders();
	
	List<Order> findByIdeIdentificacion(String identificacion);
	
	List<Order> findByFecha(String fecha_alquiler);
	
	Order findByIdeClient(Long id);

}
