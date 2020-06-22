package com.tienda.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tienda.api.models.Client;
import com.tienda.api.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import util.Constants;
import util.CustomResponse;
import util.DataValidator;

@RestController
@RequestMapping(Constants.API_VERSION)
@Api(value = "ClientControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

	// Variables globales
	public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	DataValidator validator;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation("Obtiene los clientes con id, nombre específico o todos los clientes registrados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Client.class) })
	@GetMapping(value = "/clients", headers = Constants.JSON)
	public @ResponseBody CustomResponse<List<Client>> getClients(
			@RequestParam(value = "identificacion", required = false) String identificacion,
			@RequestParam(value = "ide_cliente", required = false) Long ide_cliente) {
		System.err.println("Ide_Cliente" + ide_cliente );
		
		// buscar cliente por identificación
		if (ide_cliente != null ) {
			Client client = clientService.findById(ide_cliente);
			if (client == null) {
				return new CustomResponse<>(Boolean.FALSE, "Cliente con identificacion " + ide_cliente + " no encontrado ");
			} else {
				return new CustomResponse(client);
			}
		}

		// buscar cliente por documento cliente
		if (identificacion != null) {
			Client client = clientService.findByIdentificacion(identificacion);
			if (client == null) {
				return new CustomResponse<>(Boolean.FALSE, "Cliente con nombre " + identificacion + " no encontrado ");
			} else {
				return new CustomResponse(client);
			}
		}
		
		//Buscar todos los clientes
		List<Client> clients = new ArrayList<>();
		if (ide_cliente == null) {
			clients = clientService.findAllClients();
			if (clients.isEmpty()) {
				return new CustomResponse<>(Boolean.FALSE, Constants.NO_RESULTS);
			}
		}
		return new CustomResponse<>(Boolean.TRUE, "success", 200, clients);
	}

	@ApiOperation("Crear un nuevo cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK", response = Client.class) })
	@PostMapping(value = "/clients", headers = Constants.JSON)
	public CustomResponse<Client> createClient(@Validated @RequestBody Client client, UriComponentsBuilder uriBuilder,
			BindingResult bindingResult) {

		logger.info("Creando Cliente : {}", client.getNombre());

		if (bindingResult.hasErrors()) {

			@SuppressWarnings("unchecked")
			List<String> message = (List<String>) CustomResponse.getFielErrorResponse(bindingResult);
			return new CustomResponse<>(Boolean.FALSE, message.toString(), 409);

		} else {
			// Valida si el cliente existe en la base de datos
			if (clientService.isClientExist(client)) {
				return new CustomResponse<>(Boolean.FALSE,
						"No puede crear un cliente con identificación " + client.getIdentificacion() + " ya existe.", 409);
			}

			// Crea el cliente
			clientService.saveClient(client);
		}
		return new CustomResponse<>(Boolean.TRUE, "success", 200, client);

	}

}
