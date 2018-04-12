package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public interface ICategorieDao {
	
	public List<Categorie> getAllCategorie(Administrateur a);
	
	public Categorie addCategorie(Categorie cat);
	
	public int deleteCategorie(Categorie cat);
	
	public int updateCategorie(Categorie cat);
	
	public Categorie getCatById(Categorie cat);
	
}
