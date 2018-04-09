package fr.adaming.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fr.adaming.model.Client;

@Service
@Transactional

public class ClientServiceImpl implements IClientService{
	
	
	
	
	
	
	//Méthodes

	@Override
	public List<Client> getAllClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client addClient(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClient(Client cl) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Client isExist(Client cl) {
		// TODO Auto-generated method stub
		return null;
	}

}
