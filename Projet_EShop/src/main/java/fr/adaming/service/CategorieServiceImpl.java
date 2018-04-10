package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService{
	
	//Transformation de l'association UML en Java 
	@Autowired
	private ICategorieDao catDao;
	// Setter pour l'injection de dépendance 
	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}
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
		cat.setAdmin(a);
		return catDao.addCategorie(cat);
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
