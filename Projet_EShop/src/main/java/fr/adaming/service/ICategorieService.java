package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public interface ICategorieService {
	
	public List<Categorie> getAllCategorie(Administrateur a);
	
	public Categorie addCategorie(Categorie cat, Administrateur a);
	
	public int deleteCategorie(Categorie cat, Administrateur a);
	
	public int updateCategorie(Categorie cat, Administrateur a);
	
}
