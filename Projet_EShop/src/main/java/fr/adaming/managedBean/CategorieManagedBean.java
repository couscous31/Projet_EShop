package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{

	// Transformation de l'association UML en Java
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;

	// Setter pour l'injection de d�pendance 
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}
	
	// D�claration des attributs du ManagedBean
	private Categorie categorie;
	private Administrateur admin;
	private List<Categorie> listeCategories;
	HttpSession maSession;

	//D�claration du constructeur vide 
	public CategorieManagedBean() {
		this.categorie=new Categorie();
		this.admin=new Administrateur();
	}

	//D�claration des getters et des setters 
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}

	public ICategorieService getCatService() {
		return catService;
	}
	
	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
	
	@PostConstruct
	public void init()
	{
	maSession=	 (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	this.admin=(Administrateur) maSession.getAttribute("adminSession");
	}

	
	// M�thode Ajouter une cat�gorie
	public String addCategorie(){
		
	}


	

}
