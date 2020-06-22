package com.tienda.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tienda.api.models.Order;
import com.tienda.api.models.CreateOrder;

@Repository
@Transactional
public class OrderDaoImpl  extends AbstractSession  implements OrderDao{

	@Override
	public void saveOrder(CreateOrder order) {
		getSession().persist(order);
	}

	@Override
	public Order findById(Long id) {
		return getSession().get(Order.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrders() {
		return getSession().createQuery("from Order").list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByIdeIdentificacion(String identificacion) {
		return getSession().createQuery("from CreateOrder as o, Client as c, Product p where c.identificacion = :identificacion and o.ide_cliente = c.ide_cliente and o.ide_producto = p.ide_producto")
														.setParameter("identificacion", identificacion).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByFecha(String fecha_alquiler) {
		return getSession().createQuery("from CreateOrder where fecha_alquiler = :fecha_alquiler")
														.setParameter("fecha_alquiler", fecha_alquiler).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order findByIdeClient(Long id) {
		return (Order) getSession().createQuery("from Order where ide_cliente = :ide_cliente")
										.setParameter("ide_cliente", id)
										.uniqueResult();
	}

}
