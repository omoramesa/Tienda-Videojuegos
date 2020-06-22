package com.tienda.api.dao;

import java.util.List;

import com.tienda.api.models.Client;

public interface ClientDao {
	
	void saveClient( Client client);
	
	Client findById( Long idClient);
	
	Client findByIdentificacion ( String identificacion);
	
	List<Client> findAllClients();
	
	boolean isClientExist(Client client);
}
