package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "panierMB")
@SessionScoped

public class PanierMB implements Serializable {

	// Transfo assos avec IProduitService
	@ManagedProperty(value = "#{prService}")
	private IProduitService prodService;

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	// Transfo assos avec ICommandeService
	@ManagedProperty(value = "#{commandeService}")
	private ICommandeService comService;

	public void setComService(ICommandeService comService) {
		this.comService = comService;
	}

	// Attributs du MB
	private Panier panier;
	private LigneCommande ligneCom;
	private List<LigneCommande> listeLc;
	private double total;
	private Produit produit;
	private int quantite;

	HttpSession maSession;

	// Constructeur vide
	public PanierMB() {
		this.panier = new Panier();
		this.produit = new Produit();
		this.ligneCom = new LigneCommande();
	}

	// Méthode init
	public void init() {

		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// ajouter le panier dans la session
		maSession.setAttribute("panierSession", panier);

		// initialiser la liste des lignes de com et la mettre dans la session
		listeLc = new ArrayList<LigneCommande>();
		maSession.setAttribute("lcListe", listeLc);

		// initialiser le montant total du panier et le mettre dans la session
		total = 0;
		maSession.setAttribute("total", total);

		// initialiser la quantité des produits dans le panier et le mettre dans
		// la session
		quantite = 0;
		maSession.setAttribute("qt", quantite);

	}

	// Getter et setter
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public LigneCommande getLigneCom() {
		return ligneCom;
	}

	public void setLigneCom(LigneCommande ligneCom) {
		this.ligneCom = ligneCom;
	}

	public List<LigneCommande> getListeLc() {
		return listeLc;
	}

	public void setListeLc(List<LigneCommande> listeLc) {
		this.listeLc = listeLc;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	// Méthodes

	// Ajouter une ligne de commande dans le panier
	public String ajouterLcPanier() {

		// récup le produit dans la session
		Produit prOut = (Produit) maSession.getAttribute("produitSession");

		// vérifier que la quantité voulue soit dispo
		if (this.quantite <= prOut.getQuantite()) {

			// créer une ligne de com
			LigneCommande lcIn = new LigneCommande();

			lcIn.setProduit(prOut);
			lcIn.setQuantite(quantite);
			double prixLigne = prOut.getPrix() * quantite;
			lcIn.setPrix(prixLigne);

			// ajouter la ligne dans la liste du panier
			this.listeLc.add(lcIn);

			// mise à jour de la liste dans la session
			maSession.setAttribute("lcListe", listeLc);

			// modifier le montant total du panier
			this.total = total + prixLigne;
			maSession.setAttribute("total", total);

			return "panier";

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Le produit que vous désirez n'est plus disponible !"));
			return "rechProduitMotCle";
		}

	}

	// Valider le panier
	public String validerPanier() {

		// récup la liste de lc dans la session
		List<LigneCommande> lcOut = (List<LigneCommande>) maSession.getAttribute("lcListe");

		// vérifier que la liste n'est pas nulle
		if (lcOut != null) {

			// enregistre la commande
			Commande comIn = new Commande();
			comIn.setDate(new Date());
			comIn.setListeCl(lcOut);

			// création d'un client vide
			Client cl = new Client();

			// ajout de la commande dans la BD et récup son id
			Commande comOut = comService.addCom(comIn, cl);

			// enregistrement de la commande dans la session
			maSession.setAttribute("comSession", comOut);

			return "client";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre panier est vide."));
			return "panier";
		}

	}

	// Annuler le panier
	public String annulerPanier() {

		// vider la liste
		maSession.setAttribute("lcListe", null);

		// remmetre le montant à zero
		maSession.setAttribute("total", 0);

		return "accueil";
	}

}
