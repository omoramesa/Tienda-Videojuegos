package com.tienda.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tienda.api.models.Client;

@Repository
@Transactional
public class ClientDaoImpl extends AbstractSession implements ClientDao {

	public ClientDaoImpl() {
	}

	@Override
	public void saveClient(Client client) {
		getSession().persist(client);
	}


	@Override
	public Client findById(Long id) {
		return getSession().get(Client.class, id);
	}

	@Override
	public Client findByIdentificacion(String identificacion) {
		return (Client) getSession().createQuery("from Client where identificacion = :identificacion")
				.setParameter("identificacion", identificacion).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAllClients() {
		return getSession().createQuery("from Client").list();
	}

	@Override
	public boolean isClientExist(Client client) {

		Client clientResponse = findByIdentificacion(client.getIdentificacion());

		boolean vBalid = false;
		if (clientResponse != null) {
			vBalid = client.getIdentificacion() != clientResponse.getIdentificacion() ? true : false;
		}
		return vBalid;
	}

}
