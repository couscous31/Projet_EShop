package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped

public class ClientMB implements Serializable{
	
	//Transfo assos avec IClientService
	@ManagedProperty(value="#{clientService}")
	private IClientService clService;

	public void setClService(IClientService clService) {
		this.clService = clService;
	}
	
	//Attributs du MB
	private Client client;
	private List<Client> listeClients;
	HttpSession maSession;

	//Constructeur vide
	public ClientMB() {
		this.client=new Client();
	}
	

	//Getter et setter
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
	
	
	//Méthode du MB
	
	public void modifClient(){
		//appel de la méthode
		int verif=clService.updateClient(client);
		
	}

}
