package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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

	// Setter pour l'injection de d�pendance 
	public void setCatService(ICategorieService catService) {
		this.catService = catService;
	}
	
	// D�claration des attributs du ManagedBean
	private Categorie categorie;
	private Administrateur admin;
	private List<Categorie> listeCategories;
	HttpSession maSession;
	private boolean indice=false;
	private UploadedFile uf;

	//D�claration du constructeur vide 
	public CategorieManagedBean() {
		this.categorie=new Categorie();
		this.admin=new Administrateur();
		this.uf=new UploadedFileWrapper();
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
//	
//	@PostConstruct
//	public void init(){
//	maSession=	 (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//	this.admin=(Administrateur) maSession.getAttribute("adminSession");
//	}

	
	
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

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
	
	
	
	@PostConstruct
	public void init()
	{
		//Pour afficher liste produit dans accueil g�n�ral :
		this.listeCategories=catService.getAllCategorie();
		
		//r�cup�ration de la session ouverte:
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		//r�cup�ration de l'administrateur de la session :
		this.admin= (Administrateur) maSession.getAttribute("adminSession");
	}
	
	
	// M�thode Ajouter une cat�gorie
	public String addCategorie(){
		this.categorie.setPhoto(this.uf.getContents());
		Categorie cOut=catService.addCategorie(categorie);
		if (cOut.getId()!=0) {
			// R�cup�rer la liste des cat�gories 
			List<Categorie> listeCategories=catService.getAllCategorie();
			// MEttre � jour la session 
			System.out.println(listeCategories);
			System.out.println("============ ManagedBean Categorie - AFfichage =============");
			maSession.setAttribute("catListe", listeCategories);
			return "accueilAdmin";
		} else {
			return "ajoutCat";
		}
	}

	//M�thode Supprimer une cat�gorie 
	public String deleteCategorie(){
		System.out.println("============== Je suis dans la m�thode DELETE du MB CAtegorie");
		int verif=catService.deleteCategorie(categorie);
		System.out.println("============== Je suis � l'interieur m�thode DELETE du MB CAtegorie");
		if (verif!=0){
			listeCategories=catService.getAllCategorie();
			maSession.setAttribute("catListe", listeCategories);
			return "accueilAdmin";
		} else {
			return "deleteCat";
		}
		
	}

	
	// M�thode modifier une cat�gorie
	public String updateCategorie(){
		this.categorie.setPhoto(this.uf.getContents());
		int verif=catService.updateCategorie(categorie, admin);
		if (verif!=0){
			// R�cup�ration de la liste
			listeCategories=catService.getAllCategorie();
			// Mettre � jour la session
			maSession.setAttribute("catListe", listeCategories);
			return "accueilAdmin";
		} else {
			return "updateCat";
		}
	}
	
	//M�thode rechercher une cat�gorie par l'ID 
	public void searchCategorie(){
		this.categorie=catService.getCatById(categorie, admin);
		if(categorie!=null){
			this.indice=true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aucune cat�gorie ne correspond � cet Id"));
			this.indice=false;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
