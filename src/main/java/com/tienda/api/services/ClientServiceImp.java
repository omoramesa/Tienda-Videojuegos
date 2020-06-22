package com.tienda.api.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.api.dao.ClientDao;
import com.tienda.api.models.Client;

@Service("clientService")
@Transactional
public class ClientServiceImp implements ClientService {

	@Autowired
	private ClientDao clienteDao;

	@Override
	public void saveClient(Client client) {
		clienteDao.saveClient(client);
	}


	@Override
	public Client findById(long idClient) {
		return clienteDao.findById(idClient);
	}

	@Override
	public Client findByIdentificacion(String identificacion) {
		return clienteDao.findByIdentificacion(identificacion);
	}

	@Override
	public List<Client> findAllClients() {
		return clienteDao.findAllClients();
	}

	@Override
	public boolean isClientExist(Client client) {
		return clienteDao.isClientExist(client);
	}
}
