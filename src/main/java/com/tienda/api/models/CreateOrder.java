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
public class CreateOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ide_alquiler")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ide_alquiler;
	
	@Column(name="ide_producto")
	@NotEmpty(message="El campo ide_producto es requerido")
	@Size(min = 1, max=11)
	private String ide_producto;
	
	@Column(name="ide_cliente")
	@NotEmpty(message="El campo ide_cliente es requerido")
	@Size(min=1, max=11)
	private String ide_cliente;
	    
	@Column(name="fecha_alquiler")
	@NotEmpty(message = "La fecha de alquiler es requerida")
	@Size(max=90)
	private String fecha_alquiler;
	
	@Column(name="fecha_vence")
	@NotEmpty(message = "La fecha de vencimiento es requerida")
	@Size(max=90)
	private String fecha_vence;
	
	@Column(name="val_alquiler")
	@NotEmpty(message = "El valor del alquiler es requerido")
	@Size(max=45)
	private String val_alquiler;

	public CreateOrder() {
		super();
	}

	public CreateOrder(String ide_cliente, String ide_producto, String fecha_alquiler, String fecha_vence, String val_alquiler) {
		super();
		this.ide_cliente = ide_cliente;
		this.ide_producto = ide_producto;
		this.fecha_alquiler = fecha_alquiler;
		this.fecha_vence = fecha_vence;
		this.val_alquiler = val_alquiler;
	}
	
	
	public long getIde_alquiler() {
		return ide_alquiler;
	}

	public void setIde_alquiler(long ide_alquiler) {
		this.ide_alquiler = ide_alquiler;
	}


	
	public String getIde_producto() {
		return ide_producto;
	}

	public void setIde_producto(String ide_producto) {
		this.ide_producto = ide_producto;
	}

	public String getIde_cliente() {
		return ide_cliente;
	}

	public void setIde_cliente(String ide_cliente) {
		this.ide_cliente = ide_cliente;
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
		return "Order [ideOrder=" + ide_alquiler + ", cliente=" + ide_cliente + ", producto=" + ide_producto + ", fecha_alquiler=" + fecha_alquiler
				+ ", fecha_vence=" + fecha_vence + ", val_alquiler=" + val_alquiler + "]";
	}
}
