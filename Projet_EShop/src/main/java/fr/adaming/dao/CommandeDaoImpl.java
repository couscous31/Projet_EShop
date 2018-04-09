package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Repository

public class CommandeDaoImpl implements ICommandeDao{
	
	@Autowired
	SessionFactory sf;
	Session s;

	//le setter de SF
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	
	//Méthodes

	@Override
	public List<Commande> getAllCom(Client cl) {
		//req HQL
		String req="FROM Commande AS com WHERE com.client.id=:pIdCl";
		
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//récup du query
		Query q=s.createQuery(req);
		
		//passage des params
		q.setParameter("pIdCl", cl.getId());
		
		return q.list();
	}
	

	@Override
	public Commande addCom(Commande com) {
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//méthode de session
		s.save(com);
		
		return com;
	}

	
	@Override
	public int deleteCom(Commande com) {
		//req HQL
		String req="DELETE FROM Commande AS com WHERE com.id=:pId";
		
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//récup du query
		Query q=s.createQuery(req);
		
		//passage des params
		q.setParameter("pId", com.getId());
		
		return q.executeUpdate();
	}

}
