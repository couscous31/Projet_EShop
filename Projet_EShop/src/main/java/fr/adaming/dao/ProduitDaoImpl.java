package fr.adaming.dao;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {
	
	 @Autowired
	private SessionFactory sf;
	 
	 //setter de sf pour l'injection de dépendance :
	 public void setSf(SessionFactory sf) {
			this.sf = sf;
		}
	 
	 
	 

	@Override
	public List<Produit> getAllProduit() {
		// la requete hql :
		String req = "Select pr From Produit as pr";
		
		//ouverture de la session :
		Session s = sf.getCurrentSession();
		
		//création du query :
		 Query query=s.createQuery(req);
		 
		 List<Produit> listePr=query.list();
		 
		 for(Produit pr : listePr)
		 {
			 pr.setImage("data:image/png;base64,"+Base64.encodeBase64String(pr.getPhotoProd()));
		 }
		 
		return listePr;
	}

	

	@Override
	public Produit addProduit(Produit pr) {
		//création de la session :
		Session s=sf.getCurrentSession();
		
		//appel de la methode save de session :
		s.save(pr);
		return pr;
	}

	@Override
	public int updateProduit(Produit pr) {
		// requete hql :
		String req="UPDATE Produit as pr set pr.designation=:pDesign, pr.description=:pDescrip, pr.prix=:pPrix, pr.quantite=:pQuantite, pr.photoProd=:pPhoto where pr.id=:pIdPr ";
		
		//création de la session :
		Session s = sf.getCurrentSession();
		
		//création du query :
		Query query = s.createQuery(req);
		
		//passage des paramètres :
		query.setParameter("pDesign", pr.getDesignation());
		query.setParameter("pDescrip", pr.getDescription());
		query.setParameter("pPrix", pr.getPrix());
		query.setParameter("pQuantite", pr.getQuantite());
		query.setParameter("pPhoto", pr.getPhotoProd());
		query.setParameter("pIdPr", pr.getId());
		
		return query.executeUpdate();
	}

	@Override
	public int deleteProduit(Produit pr) {
		// la requete hql :
		String req= "DELETE from Produit as pr where pr.id=:pIdPr";
		
		//création de la session :
		Session s=sf.getCurrentSession();
		
		//création du query :
		Query query= s.createQuery(req);
		
		//passage des paramètres :
		query.setParameter("pIdPr", pr.getId());
		
		return query.executeUpdate();
	}

	
	@Override
	public Produit getProduitById(Produit pr) {
		// la requete hql :
		String req="SELECT pr from Produit as pr where pr.id=:pIdPr ";
		
		//creation de la session :
		Session s= sf.getCurrentSession();
		
		//creation du query :
		Query query= s.createQuery(req);
		
		//passage des paramètres :
		query.setParameter("pIdPr", pr.getId());
		
		return (Produit) query.uniqueResult();
	}




	@Override
	public List<Produit> produitParCategorie(Categorie cat) {
		// la requete HQL:
		String req= "SELECT pr from Produit as pr where pr.categorie.id=:pIdCat";
		
		//creation de la session :
		Session s= sf.getCurrentSession();
		
		//création du query :
		Query query=s.createQuery(req);
		
		//passage des paramètres :
		query.setParameter("pIdCat", cat.getId());
		
		return query.list();
	}




	


}
