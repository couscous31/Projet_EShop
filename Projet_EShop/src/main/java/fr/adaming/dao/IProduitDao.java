package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitDao {
	
	public List<Produit> getAllProduit();
	
	public Produit addProduit (Produit pr);
	
	public int updateProduit (Produit pr);
	
	 public int deleteProduit (Produit pr);
	 
	public Produit getProduitById (Produit pr); 
	
	public List<Produit> produitParCategorie (Categorie cat);
	

}
