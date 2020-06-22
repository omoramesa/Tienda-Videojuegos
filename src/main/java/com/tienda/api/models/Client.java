package com.tienda.api.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Client implements Serializable {

	private static final long serialVersionUID = 1682943283826480346L;

	@Id
	@Column(name = "ide_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ide_cliente;

	@Column(name = "nombre")
	@NotEmpty(message = "El campo nombre es requerido")
	@Size(max = 90)
	private String nombre;

	@Column(name = "apellido")
	@NotEmpty(message = "El campo apellido es requerido")
	@Size(max = 90)
	private String apellido;

	@Column(name = "identificacion")
	@Size(max = 10)
	private String identificacion;

	@Column(name = "celular")
	@NotEmpty(message = "El campo celular es requerido")
	@Size(max = 10)
	private String celular;

	@Column(name = "fechaNacimiento")
	@NotEmpty(message = "El campo fecha nacimiento es requerido")
	@Size(max = 10)
	private String fechaNacimiento;
	
	// Constructors
	public Client() {
		super();
	}

	public Client(String nombre, String apellido, String identificacion, String celular, String fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.celular = celular;
		this.fechaNacimiento = fechaNacimiento;
	}

	// Getter and Setters


	public String getNombre() {
		return nombre;
	}

	public long getIde_cliente() {
		return ide_cliente;
	}

	public void setIde_cliente(long ide_cliente) {
		this.ide_cliente = ide_cliente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@Override
	public String toString() {
		return "Cliente [ideCliente=" + ide_cliente + ", nombre=" + nombre + ", apellido=" + apellido + ", identificacion="
				+ identificacion + ", celular=" + celular + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
