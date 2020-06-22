package com.tienda.api.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name ="ventas")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ide_alquiler")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ide_alquiler;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="ide_producto")
	private Product producto;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="ide_cliente")
	private Client cliente;
	    
	@Column(name="fecha_alquiler")
	@NotEmpty(message = "La fecha de alquiler es requerida")
	private String fecha_alquiler;
	
	@Column(name="fecha_vence")
	@NotEmpty(message = "La fecha de vencimiento es requerida")
	private String fecha_vence;
	
	@Column(name="val_alquiler")
	@NotEmpty(message = "El valor del alquiler es requerido")
	private String val_alquiler;

	public Order() {
		super();
	}

	public Order(Client cliente, Product producto, String fecha_alquiler, String fecha_vence, String val_alquiler) {
		super();
		this.cliente = cliente;
		this.producto = producto;
		this.fecha_alquiler = fecha_alquiler;
		this.fecha_vence = fecha_vence;
		this.val_alquiler = val_alquiler;
	}
	
	
	
	
	
	public Long getIde_alquiler() {
		return ide_alquiler;
	}

	public void setIde_alquiler(Long ide_alquiler) {
		this.ide_alquiler = ide_alquiler;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public String getFecha_alquiler() {
		return fecha_alquiler;
	}

	public void setFecha_alquiler(String fecha_alquiler) {
		this.fecha_alquiler = fecha_alquiler;
	}

	public String getFecha_vence() {
		return fecha_vence;
	}

	public void setFecha_vence(String fecha_vence) {
		this.fecha_vence = fecha_vence;
	}

	public String getVal_alquiler() {
		return val_alquiler;
	}

	public void setVal_alquiler(String val_alquiler) {
		this.val_alquiler = val_alquiler;
	}

	@Override
	public String toString() {
		return "Order [ideOrder=" + ide_alquiler + ", cliente=" + cliente + ", producto=" + producto + ", fecha_alquiler=" + fecha_alquiler
				+ ", fecha_vence=" + fecha_vence + ", val_alquiler=" + val_alquiler + "]";
	}
}
