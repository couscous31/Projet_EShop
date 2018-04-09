package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;

public class CategorieDaoImpl implements ICategorieDao{

	@Autowired
	private SessionFactory sf;
	// Setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	//===============================================================
	//===============================================================
	//===============================================================
	@Override
	public List<Categorie> getAllCategorie(Administrateur a) {
		// Création de la requête 
		String req="FROM Categorie as cat WHERE cat.a.id=:pIdAdm";
		// Session
		Session s=sf.getCurrentSession();
		//Création du Query
		Query query=s.createQuery(req);
		// Passage des paramètres 
		query.setParameter("pIdAdm", a.getId());
		//Envoi et récupération du résultat 
		return query.list();
	}
	//===============================================================
	//===============================================================
	//===============================================================

	@Override
	public Categorie addCategorie(Categorie cat) {
		// TODO Auto-generated method stub
		return null;
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
