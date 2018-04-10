package fr.adaming.service;

import java.util.List;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

public interface ILigneCommandeService {
	
	public List<LigneCommande> getAllLC(Produit pr);
	
	public LigneCommande addLC(LigneCommande lc, Produit pr);
	
	public int deleteLC(LigneCommande lc, Produit pr);

}
