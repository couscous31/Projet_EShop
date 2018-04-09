package fr.adaming.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Administrateur;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements IAdminService{
	
	//Transformation de l'association UML en Java
	@Autowired
	private IAdminDao adminDao;
	//Setter pour l'injection de dépendance
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
	
	@Override
	public Administrateur isExist(Administrateur a) {
		return adminDao.isExist(a);
	}



	

}
