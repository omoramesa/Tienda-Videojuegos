
package com.tienda.api.services;

import java.util.List;

import com.tienda.api.models.Client;

public interface ClientService {

	void saveClient(Client client);

	Client findById(long idClient);

	Client findByIdentificacion(String identificion);

	List<Client> findAllClients();

	boolean isClientExist(Client client);
}
