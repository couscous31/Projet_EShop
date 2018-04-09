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
	
	//Setter pour l'injection de dépendance 
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Administrateur isExist(Administrateur a) {
		// Création de la requête
		String req="FROM Administrateur as adm WHERE adm.mail=:pMail AND adm.mdp=:pMdp";
		// Session
		Session s=sf.getCurrentSession();
		// Création du Query
		Query query=s.createQuery(req);
		// Passage des paramètres
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		// Récupération du résultat (et envoi)
		return (Administrateur) query.uniqueResult();
	}



}
