package fr.adaming.managedBean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

	public String seConnecter() {
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

				Produit prodModif = lc.getProduit();
				prodModif.setQuantite(qtDispo - qtCommande);
				prodService.updateProduit(prodModif);
			}

			// générer un PDF

			// envoyer par mail la commande PDF
			final String username = "couscous31java@gmail.com";
			final String password = "wycema31!";

			// Propriétés de la messagerie (pour gmail)
			Properties proprietes = new Properties();

			proprietes.put("mail.smtp.auth", "true");
			proprietes.put("mail.smtp.starttls.enable", "true");
			proprietes.put("mail.smtp.host", "smtp.gmail.com");
			proprietes.put("mail.smtp.port", "587");

			// Récup de la session
			Session session = Session.getInstance(proprietes, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentification() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				// Création de message
				Message msg = new MimeMessage(session);
				Multipart multipart = new MimeMultipart();

				// Set from : adresse du destinataire
				msg.setFrom(new InternetAddress(username));

				// Set to : adresse du réceptionniste
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(clOut.getMail()));

				// Set subject : objet du message
				msg.setSubject("Validation de votre commande");

				// Corps du message
				MimeBodyPart msgBody = new MimeBodyPart();
				msgBody.setContent(
						"<h1>Bonjour, votre commande a bien été validée. <br/> Vous la recevrez dans les plus brefs délais ; <br/>. Cordialement</h1>",
						"text/html");

				// Pièce jointe
				MimeBodyPart attachPart = new MimeBodyPart();
				// String attachFile = cheminPDF;

				// DataSource source = new FileDataSource(attachFile);
				// attachPart.setDataHandler(new DataHandler(source));
				// attachPart.setFileName(new File(attachFile).getName());

				// adds parts to the multipart
				multipart.addBodyPart(msgBody);
				multipart.addBodyPart(attachPart);

				// sets the multipart as message's content
				msg.setContent(multipart);

				// Envoi du message
				Transport.send(msg, msg.getAllRecipients());

				System.out.println("L'envoi du message est un succès !");

			} catch (MessagingException mex) {
				mex.printStackTrace();

			} finally {

				// vider le panier et remettre à zéro le montant total
				// vider la liste dans la session
				maSession.setAttribute("lcommandesListe", null);

				// remettre le montant total a zero dans la session
				maSession.setAttribute("total", 0);

			}
			return "accueil";
		} else {
			return "accueil";
		}

	}

	public String annulerCommande() {
		maSession.setAttribute("commandeSession", null);
		return "accueil";
	}

}
