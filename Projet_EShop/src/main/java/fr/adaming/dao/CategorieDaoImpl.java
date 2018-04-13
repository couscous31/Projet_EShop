package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
	public List<Categorie> getAllCategorie() {
		// Cr�ation de la requ�te 
		String req="Select cat FROM Categorie as cat";
		// Session
		Session s=sf.getCurrentSession();
		//Cr�ation du Query
		Query query=s.createQuery(req);
//		// Passage des param�tres 
//		query.setParameter("pIdAdm", a.getId());
		//R�cup�ration de la liste 

		List<Categorie> listeCats=query.list();
		for (Categorie categorie:listeCats){
			categorie.setImage("data:image/png;base64,"+Base64.encodeBase64String(categorie.getPhoto()));
		}
		
		return listeCats;
	}
	//===============================================================
	//===============================================================
	//===============================================================

	@Override
	public Categorie addCategorie(Categorie cat) {
		// Cr�ation de la requ�te 
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
		//Cr�ation de la requ�te
		String req="delete FROM Categorie as cat WHERE cat.id=:pIdCat";
		// Session 
		Session s=sf.getCurrentSession();
		// Cr�ation du query
		Query query=s.createQuery(req);
		// Passage des param�tres
		query.setParameter("pIdCat", cat.getId());
		//R�cup�ration du r�sultat
		return query.executeUpdate();
	}
	
	//===============================================================
	//===============================================================
	//===============================================================
	
	@Override
	public int updateCategorie(Categorie cat) {
		// Cr�ation de la requ�te
		String req="UPDATE Categorie as cat SET cat.nom=:pNom, cat.photo=:pPhoto, cat.description=:pDescr where cat.id=:pIdCat";
		//Session
		Session s=sf.getCurrentSession();
		//Cr�ation du query
		Query query=s.createQuery(req);
		// Assignation des param�tres
		query.setParameter("pNom", cat.getNom());
		query.setParameter("pPhoto", cat.getPhoto());
		query.setParameter("pDescr", cat.getDescription());
		query.setParameter("pIdCat", cat.getId());
		// Envoi et r�cup�ration de la requ�te
		return query.executeUpdate();
	}
	//===============================================================
	//===============================================================
	//===============================================================

	@Override
	public Categorie getCatById(Categorie cat) {
		// Cr�ation de la requ�te
		String req="SELECT cat FROM Categorie as cat WHERE cat.id=:pIdCat";
		//Session
		Session s=sf.getCurrentSession();
		// Cr�ation du Query
		Query query=s.createQuery(req);
		// Assignation des param�tres
		query.setParameter("pIdCat", cat.getId());
		// Envoi et r�cup�ration de la requ�te
		return (Categorie) query.uniqueResult();
	
	}




}
