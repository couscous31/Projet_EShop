package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="administrateurs")
public class Administrateur implements Serializable{
	
	// D�claration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_admin")
	private int id;
	private String mail;
	private String mdp;
	
	//Transformation de l'association UML en Java
	@OneToMany(mappedBy="admin", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Categorie> listeCategories;
	
	
	//D�claration des constructeurs 
	public Administrateur() {
		super();
	}
	
	
	public Administrateur(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}


	public Administrateur(int id, String mail, String mdp) {
		super();
		this.id = id;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	
	// D�claration des getters et des setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	//M�thode toString
	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	
	

	
}
