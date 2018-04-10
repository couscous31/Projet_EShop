package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Repository

public class LigneCommandeDaoImpl implements ILigneCommandeDao{
	
	@Autowired
	SessionFactory sf;
	Session s;
	
	//setter de SF
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	
	//Méthodes
	
	@Override
	public List<LigneCommande> getAllLC(Produit pr) {
		// req HQL
		String req="FROM LigneCommande AS lc WHERE lc.produit.id=:pIdLC";
		
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//récup du query
		Query q=s.createQuery(req);
		
		//passage des params
		q.setParameter("pIdLC", pr.getId());
		
		return q.list();
	}

	
	@Override
	public LigneCommande addLC(LigneCommande lc) {
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//méthode hibernate
		s.save(lc);
		return lc;
	}

	
	@Override
	public int deleteLC(LigneCommande lc) {
		// req HQL
		String req="DELETE FROM LigneCommande AS lc WHERE lc.id=:pId";
		
		//ouvrir la session
		s=sf.getCurrentSession();
		
		//récup du query
		Query q=s.createQuery(req);
		
		//passage des params
		q.setParameter("pId", lc.getId());
		return q.executeUpdate();
	}

}
