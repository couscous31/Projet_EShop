package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Administrateur;

@Repository
public class AdminDaoImpl implements IAdminDao{

	// Session factory
	@Autowired
	private SessionFactory sf;
	
	//Setter pour l'injection de d�pendance 
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Administrateur isExist(Administrateur a) {
		// Cr�ation de la requ�te
		String req="FROM Administrateur as adm WHERE adm.mail=:pMail AND adm.mdp=:pMdp";
		// Session
		Session s=sf.getCurrentSession();
		// Cr�ation du Query
		Query query=s.createQuery(req);
		// Passage des param�tres
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		// R�cup�ration du r�sultat (et envoi)
		return (Administrateur) query.uniqueResult();
	}



}
