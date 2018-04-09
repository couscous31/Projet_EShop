package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public interface ICommandeService {
	
	public List<Commande> getAllCom(Client cl);
	
	public Commande addCom(Commande com, Client cl);
	
	public int deleteCom(Commande com);
	
	public String genererPDF(Commande com, double montant);

}
