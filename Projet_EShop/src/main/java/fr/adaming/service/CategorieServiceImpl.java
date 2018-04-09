package fr.adaming.service;

import java.util.List;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public class CategorieServiceImpl implements ICategorieService{
	
	//Transformation de l'association UML en Java 
	private ICategorieDao catDao;

	//==================================================================
	//==================================================================
	//==================================================================
	
	
	@Override
	public List<Categorie> getAllCategorie(Administrateur a) {
		return catDao.getAllCategorie(a);
	}
	
	//==================================================================
	//==================================================================
	//==================================================================
	

	@Override
	public Categorie addCategorie(Categorie cat, Administrateur a) {
		// TODO Auto-generated method stub
		return null;
	}

	//==================================================================
	//==================================================================
	//==================================================================
	
	@Override
	public int deleteCategorie(Categorie cat, Administrateur a) {
		// TODO Auto-generated method stub
		return 0;
	}

	//==================================================================
	//==================================================================
	//==================================================================
	
	@Override
	public int updateCategorie(Categorie cat, Administrateur a) {
		// TODO Auto-generated method stub
		return 0;
	}
	//==================================================================
	//==================================================================
	//==================================================================
	

}
