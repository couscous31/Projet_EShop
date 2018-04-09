package fr.adaming.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service("commandeService")
@Transactional

public class CommandeServiceImpl implements ICommandeService{
	
	@Autowired
	private ICommandeDao comDao;
	
	//le setter 
	public void setComDao(ICommandeDao comDao) {
		this.comDao = comDao;
	}

	@Override
	public List<Commande> getAllCom(Client cl) {
		return comDao.getAllCom(cl);
	}

	@Override
	public Commande addCom(Commande com, Client cl) {
		com.setClient(cl);
		return comDao.addCom(com);
	}

	@Override
	public int deleteCom(Commande com) {
		return comDao.deleteCom(com);
	}

	@Override
	public String genererPDF(Commande com, double montant) {
		// TODO Auto-generated method stub
		return null;
	}

}
