package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="aMB")
@RequestScoped
public class AdminManagedBean implements Serializable{
	
	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{adminService}")
	private IAdminService adminService;
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;
	@ManagedProperty(value="#{prService}")
	private IProduitService produitService;
	

	// Setter pour l'injection de dépendance : obligatoire avec l'annotation @ManagedProperty
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}
	
	
	

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}




	// Déclaration des attributs transférés à la page
	private Administrateur administrateur;
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits;
	HttpSession maSession;
	
    private String orientation = "horizontal";

	
	//Déclaration du constructeur vide
	public AdminManagedBean() {
		this.administrateur=new Administrateur();
	}

	// Déclaration des getters et des setters 
	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
	
	
	
	
public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	

	//	@PostConstruct
//	public void init()
//	{
//	maSession=	 (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//	this.administrateur=(Administrateur) maSession.getAttribute("adminSession");
//	}
//	
	// Méthode se connecter
	public String seConnecter(){
		Administrateur aOut=adminService.isExist(this.administrateur);

		if (aOut!=null){

			//Récupérer la liste des catégories
			listeCategories=catService.getAllCategorie();
			listeProduits=produitService.getAllProduit();
//			System.out.println(listeCategories);
			//Ajouter le formateur dans la session 
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			//Ajouter la liste des catégories dans la session 
		
//			maSession.setAttribute("catListe",listeCategories);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCategories);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("produitListe", listeProduits);
			System.out.println("Je suis dans le Managed Bean =================================");
			return "accueilAdmin";
			
			
		} else {
			//Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec de l'authentification"));
			return "login";
		}
	}

	
	public String seDeconnecter()
	{
		
		//fermer la session ouverte :
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "accueil";
	}
	
	
	
	

}
