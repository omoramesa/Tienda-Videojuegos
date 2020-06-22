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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tienda.api.models.Order;
import com.tienda.api.models.CreateOrder;
import com.tienda.api.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import util.Constants;
import util.CustomErrorType;

@RestController
@RequestMapping(Constants.API_VERSION)
@Api(value="ClientControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	//Variables Globales
	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private CustomErrorType customErrorType;
	
	@Autowired
	OrderService orderService;
	
	// ------------------- GET Pedido----------------------------------------------------------------------------------
	
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@ApiOperation("Obtiene los pedidos por ide alquiler, ide cliente o todos las ventas")
		@ApiResponses(value= { @ApiResponse(code =200, message = "OK", response = Order.class) })
		@RequestMapping(value="/orders", method = RequestMethod.GET, headers = Constants.JSON)
		public @ResponseBody ResponseEntity<List<Order>> getOrders(@RequestParam(value="ide_cliente", required=false) Long ideClient, 
																   @RequestParam(value = "ide_alquiler", required = false) Long idAlquiler,
																   @RequestParam(value = "fecha_alquiler", required = false) String fecha_alquiler){

			//buscar por ide de alquiler
			if(idAlquiler !=null &&idAlquiler > 0){
				Order order =  orderService.findById(idAlquiler);
				if(order == null){
					return new ResponseEntity(new CustomErrorType("Pedido con ide_alquiler" + idAlquiler + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
				}else {
					return new ResponseEntity(order, HttpStatus.OK);
				}
			}
			
			//buscar por ide_cliente
			if(ideClient !=null){
				Order orders = orderService.findByIdeClient(ideClient);
				if(orders == null){
					return new ResponseEntity(new CustomErrorType("Pedido con ide_cliente " + ideClient + " no encontrado ", MessageType.ERROR ), HttpStatus.NOT_FOUND);
				}else {
					return new ResponseEntity(orders, HttpStatus.OK);
				}
			}
			
			List<Order> orders = new ArrayList<>();
			//Buscar todos las ventas registradas
			if(idAlquiler == null && ideClient == null && fecha_alquiler == null){
				orders = orderService.findAllOrders();
				if(orders.isEmpty()){				
					return new ResponseEntity(new CustomErrorType(Constants.NO_RESULTS, MessageType.INFO ), HttpStatus.NOT_FOUND);
				}
			}
			
			//Buscar todas las ventas por fecha
			
			if(fecha_alquiler != null){
				orders = orderService.findByFecha(fecha_alquiler);
				if(orders.isEmpty()){				
					return new ResponseEntity(new CustomErrorType(Constants.NO_RESULTS, MessageType.INFO ), HttpStatus.NOT_FOUND);
				}
			}
			
			return new ResponseEntity(orders, HttpStatus.OK);
		}
		
		// ------------------- GET Pedido----------------------------------------------------------------------------------
		
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@ApiOperation("Obtiene el balance de un cliente")
			@ApiResponses(value= { @ApiResponse(code =200, message = "OK", response = Order.class) })
			@RequestMapping(value="/ordersXClient", method = RequestMethod.GET, headers = Constants.JSON)
			public @ResponseBody ResponseEntity<List<Order>> getOrders(@RequestParam(value = "identificacion", required = false) String identificacion){

				
				List<Order> orders = new ArrayList<>();
				//buscar por documento del cliente
				if(identificacion !=null){
					orders = orderService.findByIdeIdentificacion(identificacion);
					if(orders.isEmpty()){
						return new ResponseEntity(new CustomErrorType("No se encontraron pedidos para el cliente " + identificacion, MessageType.ERROR ), HttpStatus.NOT_FOUND);
					}else {
						return new ResponseEntity(orders, HttpStatus.OK);
					}
				}
				
				return new ResponseEntity(orders, HttpStatus.OK);
			}

		
		// ------------------- POST Venta----------------------------------------------------------------------------------
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@ApiOperation("Creando la venta")
		@ApiResponses(value= { @ApiResponse(code =201, message = "OK", response = CreateOrder.class) })
		@RequestMapping(value="/orders", method = RequestMethod.POST, headers = Constants.JSON)
		public ResponseEntity<CreateOrder> createOrder(@Validated @RequestBody CreateOrder createorder, UriComponentsBuilder uriBuilder, BindingResult bindingResult){
			
			logger.info("Creando la venta : {}", createorder.toString());

			if (bindingResult.hasErrors()) {
				return new ResponseEntity(new CustomErrorType(bindingResult.getAllErrors().toString(), MessageType.ERROR),HttpStatus.BAD_REQUEST);
			} else {

				//Creando la venta
				orderService.saveOrder(createorder);	
				
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(uriBuilder.path(Constants.API_VERSION+ Constants.ORDERS + "{id}").buildAndExpand(createorder.getIde_alquiler()).toUri());
				return new ResponseEntity(createorder, headers, HttpStatus.CREATED);
	        }
		} 
		
		
		
		
		
		
		
}
