package com.tienda.api.controllers;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.tienda.api.models.Product;
import com.tienda.api.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import util.Constants;
import util.CustomErrorType;

@Controller
@RequestMapping(Constants.API_VERSION)
@Api(value="ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	//Variables Globales
	public static final Logger logger =  LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	
	// ------------------- GET Productoss----------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation("Obtener productos por ide, título, dorector, protagonista, productor o marca")
	@ApiResponses(value= { @ApiResponse(code =200, message = "OK", response = Product.class) })
	@RequestMapping(value="/products", method = RequestMethod.GET, headers = Constants.JSON)
	public @ResponseBody ResponseEntity<List<Product>> getProducts(@RequestParam(value="titulo", required=false) String titulo,
																   @RequestParam(value = "ide_producto", required = false) Long ide_producto,
																   @RequestParam ( value = "director", required = false) String director, 
																   @RequestParam ( value = "protagosnista", required = false) String protagonista,
																   @RequestParam ( value = "productor", required = false) String productor,
																   @RequestParam ( value = "marca", required = false) String marca){
		
		//buscar producto por ide producto
		if(ide_producto !=null && ide_producto > 0){
			Product product = productService.findById(ide_producto);
			if(product == null){
				return new ResponseEntity(new CustomErrorType("Producto con identifiacor " + ide_producto + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(product, HttpStatus.OK);
			}
		}
		
		//Buscar producto por título
		if(titulo !=null){
			Product product = productService.findByName(titulo);
			if(product == null){
				return new ResponseEntity(new CustomErrorType("Producto con nombre " + titulo + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(product, HttpStatus.OK);
			}
		}
		
		List<Product> products = new ArrayList<>();
		//Buscar producto por director
		if(director !=null){
			products = productService.findByDirector(director);
			if(products.isEmpty()){
				return new ResponseEntity(new CustomErrorType("Productos con director " + director + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(products, HttpStatus.OK);
			}
		}
		
		//Buscar producto por protagonista
		if(protagonista !=null){
			products = productService.findByProtagonista(protagonista);
			if(products.isEmpty()){
				return new ResponseEntity(new CustomErrorType("Productos con protagonista " + protagonista + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(products, HttpStatus.OK);
			}
		}
		
		//Buscar producto por productor
		if(productor !=null){
			products = productService.findByProductor(productor);
			if(products.isEmpty()){
				return new ResponseEntity(new CustomErrorType("Productos con productor " + productor + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(products, HttpStatus.OK);
			}
		}
		
		//Buscar producto por marca
		if(marca !=null){
			products = productService.findByMarca(marca);
			if(products.isEmpty()){
				return new ResponseEntity(new CustomErrorType("Productos con marca " + marca + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity(products, HttpStatus.OK);
			}
		}
		
		
		//Si la busqueda no es por ide producto 
		if(titulo == null && ide_producto == null && director == null && protagonista == null && productor == null && marca == null){
			products = productService.findAllProducts();
			if(products.isEmpty() ){
				return new ResponseEntity(new CustomErrorType(Constants.NO_RESULTS, MessageType.INFO ), HttpStatus.NOT_FOUND);
			}
		}
		
		return new ResponseEntity(products, HttpStatus.OK);
	}
	
	// ------------------- POST Product----------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation("Create product")
	@ApiResponses(value= { @ApiResponse(code =201, message = "OK", response = Product.class) })
	@RequestMapping(value="/products", method = RequestMethod.POST, headers = Constants.JSON)
	public ResponseEntity<Product> createProduct(@Validated @RequestBody Product product, UriComponentsBuilder uriBuilder, BindingResult bindingResult){
		
		logger.info("Creating Product : {}", product.toString());

		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new CustomErrorType(bindingResult.getAllErrors().toString(), MessageType.ERROR),HttpStatus.BAD_REQUEST);
		} else {
			
			//Validar si el producto existe o no
			if(productService.isProductExist(product)){
				return new ResponseEntity(
						new CustomErrorType("No se puede crear el producto con nombre: " 
								+ product.getTitulo() + " porque ya existe." 
								, MessageType.INFO ),HttpStatus.CONFLICT);
			}
			//Crea el producto
			productService.saveProduct(product);	
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uriBuilder.path(Constants.API_VERSION+ Constants.PRODUCTS + "{id}").buildAndExpand(product.getIde_producto()).toUri());
			return new ResponseEntity(product, headers, HttpStatus.CREATED);
        }
	} 
	
	
	
}
