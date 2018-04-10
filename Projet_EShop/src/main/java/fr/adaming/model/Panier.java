package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

public class Panier implements Serializable{
	
	//Attributs
	private int idP;
	
	//Transfo assos avec ligne de commande
	private List<LigneCommande> listeLc;

	//Constructeurs
	public Panier() {
		super();
	}

	public Panier(int idP) {
		super();
		this.idP = idP;
	}

	//Getter et setter
	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public List<LigneCommande> getListeLc() {
		return listeLc;
	}

	public void setListeLc(List<LigneCommande> listeLc) {
		this.listeLc = listeLc;
	}
	
	
	
	

}
