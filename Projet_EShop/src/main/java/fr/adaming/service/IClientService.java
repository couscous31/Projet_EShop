package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;

public interface IClientService {
	
	public List<Client> getAllClient();
	
	public Client addClient(Client cl);
	
	public int updateClient(Client cl);
	
	public int deleteClient(Client cl);
	
	public Client isExist(Client cl);

}
