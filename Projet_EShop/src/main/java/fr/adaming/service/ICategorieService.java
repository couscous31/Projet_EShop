package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public interface ICategorieService {
	
	public List<Categorie> getAllCategorie();
	
	public Categorie addCategorie(Categorie cat);
	
	public int deleteCategorie(Categorie cat);
	
	public int updateCategorie(Categorie cat, Administrateur a);
	
	public Categorie getCatById(Categorie cat, Administrateur a);
	
}
