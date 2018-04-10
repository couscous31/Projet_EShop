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

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.service.ICommandeService;

@ManagedBean(name = "comMB")
@RequestScoped

public class CommandeMB implements Serializable {

	// Transfo assos avec service
	@ManagedProperty(value = "commandeService")
	private ICommandeService comService;

	public void setComService(ICommandeService comService) {
		this.comService = comService;
	}

	// Attribut du MB
	private Commande commande;
	private List<Commande> listeCom;
	private Client client;
	HttpSession maSession;

	// Constructeur vide
	public CommandeMB() {
		this.commande = new Commande();
	}

	// Méthode init
	@PostConstruct
	public void init() {
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.client = (Client) maSession.getAttribute("clientSession");
	}

	// Getter et setter
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<Commande> getListeCom() {
		return listeCom;
	}

	public void setListeCom(List<Commande> listeCom) {
		this.listeCom = listeCom;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// Méthodes

	public String ajoutCom(){
		//appel de la méthode
		Commande comAdd=comService.addCom(commande, client);
		
		if(comAdd.getId() != 0){
			//récup et affiche la new liste dans la session
			listeCom = comService.getAllCom(client);
			maSession.setAttribute("commandesListe", listeCom);
			return "accueilCom";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué !"));
			return "ajoutCom";
		}
		
	}
	
	
	public void supCom(){
		//appel de la méthode
		int verif = comService.deleteCom(commande);
		
		if(verif != 0){
			//récup et affiche la new liste dans la session
			listeCom = comService.getAllCom(client);
			maSession.setAttribute("commandesListe", listeCom);
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué !"));
		}
	}

}
