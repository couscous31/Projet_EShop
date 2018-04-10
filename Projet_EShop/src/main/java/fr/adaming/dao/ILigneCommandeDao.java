package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

public interface ILigneCommandeDao {
	
	public List<LigneCommande> getAllLC(Produit pr);
	
	public LigneCommande addLC(LigneCommande lc);
	
	public int deleteLC(LigneCommande lc);

}
