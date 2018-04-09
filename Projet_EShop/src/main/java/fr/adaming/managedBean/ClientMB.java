package fr.adaming.managedBean;

import java.io.Serializable;
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
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped

public class ClientMB implements Serializable {

	// Transfo assos avec IClientService
	@ManagedProperty(value = "#{clientService}")
	private IClientService clService;

	public void setClService(IClientService clService) {
		this.clService = clService;
	}

	// Attributs du MB
	private Client client;
	private List<Client> listeClients;
	HttpSession maSession;

	// Constructeur vide
	public ClientMB() {
		this.client = new Client();
	}
	
	//Méthode init
	@PostConstruct
	public void init(){
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
	
	public void seConnecter(){
		
	}

}
