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
@Table(name ="productos")
public class Product implements Serializable{
	
	private static final long serialVersionUID = -8157928487443214003L;

	@Id
	@Column(name="ide_producto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ide_producto;
	
	@Column(name="titulo")
	@NotEmpty(message = "El campo título es requerido")
	@Size(max=90)
	private String titulo;
	
	@Column(name="director")
	@NotEmpty(message = "El campo director es requerido")
	@Size(max=90)
	private String director;
	
	@Column(name="protagonista")
	@NotEmpty(message = "El campo protagonista es requerido")
	@Size(max=90)
	private String protagonista;
	
	@Column(name="productor")
	@NotEmpty(message = "El campo productor es requerido")
	@Size(max=90)
	private String productor;
	
	@Column(name="marca")
	@NotEmpty(message = "El campo marca es requerido")
	@Size(max=45)
	private String marca;
	
	@Column(name="anio")
	@NotEmpty(message = "El campo año es requerido")
	@Size(max=10)
	private String anio;
	
	
	@Column(name="tecnologia")
	@NotEmpty(message = "El campo año es requerido")
	@Size(max=20)
	private String tecnologia;
	
	@Column(name="precio")
	@NotEmpty(message = "El campo precio es requerido")
	@Size(max=10)
	private String precio;
	
	
	//Constructors
	public Product() {
		super();
	}

	public Product(String titulo, String director, String protagonista, String productor, String marca, String anio, String tecnologia, String precio) {
		super();
		this.titulo =titulo;
		this.director = director;
		this.protagonista = protagonista;
		this.productor = productor;
		this.marca = marca;
		this.anio = anio;
		this.tecnologia = tecnologia;
		this.precio = precio;
	}

	

	

	public long getIde_producto() {
		return ide_producto;
	}

	public void setIde_producto(long ide_producto) {
		this.ide_producto = ide_producto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}

	public String getProductor() {
		return productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Product [ideProducto=" + ide_producto + ", titulo=" + titulo + ", director=" + director + ", protagonista="
				+ protagonista + ", productor=" + productor + ", marca=" + marca + ",  anio=" + anio + " , tecnologia=" + tecnologia + " , precio=" + precio + "]";
	}	
	
}

