package fr.adaming.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("prService")
@Transactional
public class ProduitServiceImpl implements IProduitService {
	
	//transformation uml en java avec springIOC:
	@Autowired
	private IProduitDao produitDao;

	@Override
	public List<Produit> getAllProduit() {
		
		return produitDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit pr) {
		
		return produitDao.addProduit(pr);
	}

	@Override
	public int updateProduit(Produit pr) {
		// TODO Auto-generated method stub
		return produitDao.updateProduit(pr);
	}

	@Override
	public int deleteProduit(Produit pr) {
		// TODO Auto-generated method stub
		return produitDao.deleteProduit(pr) ;
	}

	@Override
	public Produit getProduitById(Produit pr) {
		// TODO Auto-generated method stub
		return produitDao.getProduitById(pr);
	}

	@Override
	public List<Produit> produitParCategorie(Categorie cat) {
		
		return produitDao.produitParCategorie(cat);
	}
	
	

	@Override
	public List<Produit> getParMotCle(String motCle) {
		// récupérer la liste des produits :
		List<Produit> listePr=produitDao.getAllProduit();
		
		//initialiser la liste récupérer avec les mots clés :
		List<Produit> listeRech = new ArrayList<Produit>();
		
		for(Produit pr : listePr)
		{
			
			if(pr.getDesignation().contains(motCle) || pr.getDescription().contains(motCle))
			{
				listeRech.add(pr);
			}
			
		}
		
		return listeRech;
	}
	

}
