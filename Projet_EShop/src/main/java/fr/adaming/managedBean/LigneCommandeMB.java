package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name="lcMB")
@RequestScoped

public class LigneCommandeMB implements Serializable{
	
	@Autowired
	private ILigneCommandeService lcService;

	public void setLcService(ILigneCommandeService lcService) {
		this.lcService = lcService;
	}

	//Attributs du MB
	private LigneCommande lignecom;
	private List<LigneCommande> listeLc;
	HttpSession maSession;
	private Produit produit;

	//Constructeur vide
	public LigneCommandeMB() {
		this.lignecom=new LigneCommande();
	}

	//Getter et setter
	public LigneCommande getLignecom() {
		return lignecom;
	}

	public void setLignecom(LigneCommande lignecom) {
		this.lignecom = lignecom;
	}

	public List<LigneCommande> getListeLc() {
		return listeLc;
	}

	public void setListeLc(List<LigneCommande> listeLc) {
		this.listeLc = listeLc;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	//Méthodes
	
	public String ajoutLC(){
		//appel de la méthode
		LigneCommande lcAdd=lcService.addLC(lignecom, produit);
		
		if(lcAdd != null){
			//récup et affiche la new liste dans la session
			listeLc = lcService.getAllLC(produit);
			maSession.setAttribute("lcommandesListe", listeLc);
			return "ligneCommande";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué !"));
			return "ajoutLc";
		}
	}
	
	public void supLC(){
		//appel de la méthode
		int verif = lcService.deleteLC(lignecom, produit);
		
		if(verif != 0){
			//récup et affiche la new liste dans la session
			listeLc = lcService.getAllLC(produit);
			maSession.setAttribute("lcommandesListe", listeLc);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué !"));
		}
	}
	
	
	
}
