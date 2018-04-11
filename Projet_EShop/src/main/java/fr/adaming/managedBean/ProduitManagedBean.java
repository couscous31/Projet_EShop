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

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable {
	
	//transformation de l'association uml en java :
	@ManagedProperty(value="#{prService}")
	private IProduitService produitService;
	
	/*@ManagedProperty(value="#{catService}")
	private ICategorieService catService; */

	//setter de produitService pour l'injection de dépendance:
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	
	//déclaration des attributs du ManagedBean :
	private Produit produit;
	private Categorie categorie;
	private Administrateur administrateur;
	private List<Produit> listeProduit;
	private UploadedFile uf;
	private boolean indice;
	
	HttpSession sessionProd;
	
	private String motCle;
	
	private List<Produit> filtreProduits;
    private Produit selectedProduit;

	
	
	public ProduitManagedBean() {
		this.produit=new Produit();
		this.categorie=new Categorie();
		this.uf=new UploadedFileWrapper();
		this.indice=false;
	}
	
	@PostConstruct
	public void init()
	{
		//Pour afficher liste produit dans accueil général :
		this.listeProduit=produitService.getAllProduit();
		
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
	
	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}
	
	public List<Produit> getFiltreProduits() {
		return filtreProduits;
	}

	public void setFiltreProduits(List<Produit> filtreProduits) {
		this.filtreProduits = filtreProduits;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public boolean isIndice() {
		return indice;
	}

	public void setIndice(boolean indice) {
		this.indice = indice;
	}
	
	
	public Produit getSelectedProduit() {
		return selectedProduit;
	}

	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}
	
	
	
	//Méthodes métiers:	

	

	//ajouter un produit :
	public String ajouterProduit()
	{
		this.produit.setPhotoProd(this.uf.getContents());
		
		//appel de la méthode ajouter :
		Produit prAjout= produitService.addProduit(produit);
		
		if(prAjout.getId()!=0)
		{
			//remettre à jour la liste
			List<Produit> liste=produitService.getAllProduit();
			
			sessionProd.setAttribute("produitListe", liste);
			
			return "accueilAdmin";
		}
		else 
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout du produit n'a pas marché"));
			return "ajouterProduit";
		}
	}

	
	//modifier les attributs d'un produit :
	public void editProduit(RowEditEvent event)
	{
		//appel de la methode :
		produitService.updateProduit((Produit) event.getObject());
		
		//récupérer la nouvelle liste :
		List<Produit> liste = produitService.getAllProduit();
		
		//mettre à jour la liste :
		sessionProd.setAttribute("produitListe", liste);
	}
	
	
	//supprimer un produit de la liste :
	public void supprimerProduit()
	{
		
		int prSuppr=produitService.deleteProduit(produit);
		
		if(prSuppr!=0)
		{
			List<Produit> liste = produitService.getAllProduit();
			sessionProd.setAttribute("produitListe", liste);
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression n'a pas marché"));
		}
	}
	
	
	//rechercher un produit avec son id :
	public void rechercherProduitById()
	{
		try{
			Produit prSear=produitService.getProduitById(produit);
			this.produit=prSear;
			this.indice=true;
			
		}
		catch(Exception ex)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche effectuée n'est pas bonne"));
			this.indice=false;
		}
	}
	
	
	//recherche par mot clé :
	public String rechMotCle()
	{
		List<Produit> listeRech=produitService.getParMotCle(motCle);
		
		sessionProd.setAttribute("rechListe", listeRech);
		
		return "rechProduitMotCle";
				
	}
	
	
	//afficher produit par categorie :
	public String produitParCategorie()
	{
		List<Produit> liste = produitService.produitParCategorie(produit, categorie);
		
		if(liste!=null)
		{
			//récupérer la nouvelle liste :
			//List<Produit> liste=produitService.getAllProduit();
			
			//la mettre à jour :
			sessionProd.setAttribute("produitListe", liste);
			
			return "produitParCategorie";
		}
		else
		{
			return "accueil";
		}
	}

	
		

}
