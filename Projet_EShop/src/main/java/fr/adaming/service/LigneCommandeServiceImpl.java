package fr.adaming.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Service("lignecomService")
@Transactional

public class LigneCommandeServiceImpl implements ILigneCommandeService{
	
	@Autowired
	private ILigneCommandeDao lcDao;

	//le setter
	public void setLcDao(ILigneCommandeDao lcDao) {
		this.lcDao = lcDao;
	}

	//Méthodes
	
	@Override
	public List<LigneCommande> getAllLC(Produit pr) {
		return lcDao.getAllLC(pr);
	}

	@Override
	public LigneCommande addLC(LigneCommande lc, Produit pr) {
		lc.setProduit(pr);
		return lcDao.addLC(lc);
	}

	@Override
	public int deleteLC(LigneCommande lc, Produit pr) {
		lc.setProduit(pr);
		return lcDao.deleteLC(lc);
	}

}
