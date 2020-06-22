package com.tienda.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ApiTiendaAccountApplication {

	  @RequestMapping("/")
	  @ResponseBody
	  String home() {
	      return "Hello World!";
	  }
	  
	public static void main(String[] args) {
		SpringApplication.run(ApiTiendaAccountApplication.class, args);
	}
}
