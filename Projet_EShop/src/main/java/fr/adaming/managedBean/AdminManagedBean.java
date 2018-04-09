package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="aMB")
@RequestScoped
public class AdminManagedBean implements Serializable{
	
	//Transformation de l'association UML en Java
	@ManagedProperty(value="#{adminService}")
	private IAdminService adminService;
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;
	

	// Setter pour l'injection de dépendance : obligatoire avec l'annotation @ManagedProperty
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}

	// Déclaration des attributs transférés à la page
	private Administrateur admin;
	private List<Categorie> listeCategories;
	private List<Produit> listeProduits;
	
	//Déclaration du constructeur vide
	public AdminManagedBean() {
		this.admin=new Administrateur();
	}

	// Déclaration des getters et des setters 
	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
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
	
	
	// Méthode se connecter
	public String seConnecter(){
		Administrateur aOut=adminService.isExist(this.admin);

		if (aOut!=null){

			//Récupérer la liste des catégories
			listeCategories=catService.getAllCategorie(aOut);
			//Ajouter le formateur dans la session 
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			//Ajouter la liste des catégories dans la session 
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", listeCategories);
			return "accueilAdmin";
			
		} else {
			//Message d'erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec de l'authentification"));
			return "login";
		}
	}

	
	
	
	
	
	

}
