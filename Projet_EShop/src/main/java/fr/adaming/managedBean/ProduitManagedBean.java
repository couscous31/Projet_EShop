package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	
	//transformation de l'association uml en java :
	@ManagedProperty(value="#{prService}")
	private IProduitService produitService;

	//setter de produitService pour l'injection de dépendance:
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	
	//déclaration des attributs du ManagedBean :
	private Produit produit;
	private Administrateur administrateur;
	private List<Produit> listeProduit;
	private UploadedFile uf;
	
	HttpSession sessionProd;

	
	
	public ProduitManagedBean() {
		this.produit=new Produit();
		this.uf=new UploadedFileWrapper();
	}
	
	@PostConstruct
	public void init()
	{
		
		//récupération de la session ouverte:
		this.sessionProd=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		//récupération de l'administrateur de la session :
		this.administrateur= (Administrateur) sessionProd.getAttribute("adminSession");
	}

	
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public List<Produit> getListeProduit() {
		return listeProduit;
	}

	public void setListeProduit(List<Produit> listeProduit) {
		this.listeProduit = listeProduit;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}
	
	
	//Méthodes métiers:
	
	
	
	

}
