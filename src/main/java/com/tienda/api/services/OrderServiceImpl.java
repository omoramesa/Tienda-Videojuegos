package com.tienda.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.api.dao.OrderDao;
import com.tienda.api.models.Order;
import com.tienda.api.models.CreateOrder;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService  {
	
	@Autowired
	private OrderDao orderDao;
	

	@Override
	public void saveOrder(CreateOrder order) {
		orderDao.saveOrder(order);
	}

	@Override
	public Order findById(Long id) {
		return orderDao.findById(id);
	}
	
	@Override
	public List<Order> findByIdeIdentificacion(String identificacion) {
		return orderDao.findByIdeIdentificacion(identificacion);
	}
	
	@Override
	public List<Order> findByFecha(String fecha_alquiler) {
		return orderDao.findByFecha(fecha_alquiler);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderDao.findAllOrders();
	}

	@Override
	public Order findByIdeClient(Long id) {
		return orderDao.findByIdeClient(id);
	}
	
}
