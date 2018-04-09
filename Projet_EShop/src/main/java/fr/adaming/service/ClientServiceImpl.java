package fr.adaming.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("clientService")
@Transactional

public class ClientServiceImpl implements IClientService{
	
	@Autowired
	private IClientDao clDao;

	//Setter de clDao
	public void setClDao(IClientDao clDao) {
		this.clDao = clDao;
	}	
	
	
	//Méthodes


	@Override
	public List<Client> getAllClient() {
		return clDao.getAllClient();
	}

	@Override
	public Client addClient(Client cl) {
		return clDao.addClient(cl);
	}

	@Override
	public int updateClient(Client cl) {
		return clDao.updateClient(cl);
	}

	@Override
	public int deleteClient(Client cl) {
		return clDao.deleteClient(cl);
	}

	@Override
	public Client isExist(Client cl) {
		return clDao.isExist(cl);
	}

}
