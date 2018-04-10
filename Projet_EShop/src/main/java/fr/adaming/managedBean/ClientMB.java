package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "clMB")
@RequestScoped

public class ClientMB implements Serializable {

	// Transfo assos avec IClientService
	@ManagedProperty(value = "#{clientService}")
	private IClientService clService;

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	// Transfo assos avec ICommandeService
	@ManagedProperty(value = "#{commandeService}")
	private ICommandeService comService;

	public void setComService(ICommandeService comService) {
		this.comService = comService;
	}

	// Transfo assos avec IProduitService
	@ManagedProperty(value = "#{prService}")
	private IProduitService prodService;

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	// Attributs du MB
	private Client client;
	private List<Client> listeClients;
	HttpSession maSession;

	// Constructeur vide
	public ClientMB() {
		this.client = new Client();
	}

	// Méthode init
	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Getter et setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(List<Client> listeClients) {
		this.listeClients = listeClients;
	}

	// Méthode du MB

	public void modifClient() {
		// appel de la méthode
		int verif = clService.updateClient(client);

		if (verif != 0) {
			Client clModif = clService.isExist(client);
			maSession.setAttribute("clientModif", clModif);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a échoué !"));
		}
	}

	public void supClient() {
		// appel de la méthode
		int verif = clService.deleteClient(client);

		if (verif != 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression est un succès !"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué !"));
		}
	}

	public void seConnecter() {
		// appel de la méthode
		Client clOut = clService.isExist(client);

		if (clOut != null) {

			// récup la liste de ligne de commande dans la session
			List<LigneCommande> listeLc = (List<LigneCommande>) maSession.getAttribute("lcommandesListe");

			// enregistrer de la commande
			Commande comIn = new Commande();
			comIn.setDate(new Date());
			comIn.setListeCl(listeLc);

			// ajouter la commande dans la BD et récup de son id
			Commande comOut = comService.addCom(comIn, clOut);

			// récup le montant total
			double total = (double) maSession.getAttribute("total");

			// modifier la quantité restante de chaque produit dans la BD
			for (LigneCommande lc : listeLc) {

				// qté produitBD - qté produitCommande
				int qtCommande = (int) lc.getQuantite();
				int qtDispo = lc.getProduit().getQuantite();

			}

		}

	}

}
