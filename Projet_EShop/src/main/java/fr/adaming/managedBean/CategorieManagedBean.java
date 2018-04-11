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
import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{

	// Transformation de l'association UML en Java
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;

	// Setter pour l'injection de dépendance 
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}
	
	// Déclaration des attributs du ManagedBean
	private Categorie categorie;
	private Administrateur admin;
	private List<Categorie> listeCategories;
	HttpSession maSession;
	private UploadedFile uf;

	//Déclaration du constructeur vide 
	public CategorieManagedBean() {
		this.categorie=new Categorie();
		this.admin=new Administrateur();
		this.uf=new UploadedFileWrapper();
	}

	//Déclaration des getters et des setters 
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

	
	
	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// Méthode Ajouter une catégorie
	public String addCategorie(){
		this.categorie.setPhoto(this.uf.getContents());
		Categorie cOut=catService.addCategorie(categorie, admin);
		if (cOut.getId()!=0) {
			// Récupérer la liste des catégories 
			List<Categorie> listeCategories=catService.getAllCategorie(admin);
			// MEttre à jour la session 
			maSession.setAttribute("categorieListe", listeCategories);
			return "accueilAdmin";
		} else {
			return "ajoutCat";
		}
	}

	//Méthode Supprimer une catégorie 
	public String deleteCategorie(){
		int verif=catService.deleteCategorie(categorie, admin);
		if (verif!=0){
			listeCategories=catService.getAllCategorie(admin);
			maSession.setAttribute("categoriesListe", listeCategories);
			return "accueilAdmin";
		} else {
			return "deleteCat";
		}
		
	}

	

}
