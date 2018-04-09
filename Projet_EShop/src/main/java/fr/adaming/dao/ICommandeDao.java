package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeDao {
	
	public List<Commande> getAllCom(Client cl);
	
	public Commande addCom(Commande com);
	
	public int deleteCom(Commande com);

}
