package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

@Repository
public class CategorieDaoImpl implements ICategorieDao{

	@Autowired
	private SessionFactory sf;
	// Setter pour l'injection de d�pendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	//===============================================================
	//===============================================================
	//===============================================================
	@Override
	public List<Categorie> getAllCategorie(Administrateur a) {
		// Cr�ation de la requ�te 
		String req="FROM Categorie as cat WHERE cat.admin.id=:pIdAdm";
		// Session
		Session s=sf.getCurrentSession();
		//Cr�ation du Query
		Query query=s.createQuery(req);
		// Passage des param�tres 
		query.setParameter("pIdAdm", a.getId());
		//Envoi et r�cup�ration du r�sultat 
		return query.list();
	}
	//===============================================================
	//===============================================================
	//===============================================================

	@Override
	public Categorie addCategorie(Categorie cat) {
		// R�cup�ration de la session
		Session s=sf.getCurrentSession();
		s.save(cat);
		return cat;
	}
	//===============================================================
	//===============================================================
	//===============================================================
	@Override
	public int deleteCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}
	//===============================================================
	//===============================================================
	//===============================================================
	@Override
	public int updateCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return 0;
	}
	//===============================================================
	//===============================================================
	//===============================================================




}
