package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "clients")

public class Client implements Serializable {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cl")
	private long id;
	private String nom;
	private String prenom;
	private String adresse;
	private String mail;
	private String mdp;
	private String tel;

	// Transfo de l'assos avec Commande
	@OneToMany(mappedBy = "client")
	private List<Commande> listeCommande;

	// Constructeurs
	public Client() {
		super();
	}

	public Client(String nom, String prenom, String adresse, String mail, String mdp, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.mdp = mdp;
		this.tel = tel;
	}

	public Client(long id, String nom, String prenom, String adresse, String mail, String mdp, String tel) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.mdp = mdp;
		this.tel = tel;
	}

	// Getter et setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Commande> getListeCommande() {
		return listeCommande;
	}

	public void setListeCommande(List<Commande> listeCommande) {
		this.listeCommande = listeCommande;
	}

}
