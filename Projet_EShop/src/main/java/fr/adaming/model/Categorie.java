package fr.adaming.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	
	// D�claration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int id;
	private String nom;
	private byte[] photo;
	private String description;
	private String image;
	
	//Transformation de l'association UML en Java 
	@OneToMany(mappedBy="categorie", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Produit> listeProduits;
	@ManyToOne
	@JoinColumn(name="ad_id", referencedColumnName="id_admin")
	private Administrateur admin;
	
	//D�claration des constructeurs 
	public Categorie() {
		super();
	}
	
	
	public Categorie(String nom, byte[] photo, String description, List<Produit> listeProduits, String image) {
		super();
		this.nom = nom;
		this.photo = photo;
		this.description = description;
		this.listeProduits = listeProduits;
	}


	public Categorie(int id, String nom, byte[] photo, String description, String image) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}
	
	//D�claration des getters et des setters 
	
	public int getId() {
		return id;
	}
	public void setIdCategorie(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNomCategorie(String nom) {
		this.nom = nom;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	//M�thode toString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + id + ", nomCategorie=" + nom + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]" + image;
	}
	
	

}
