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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie implements Serializable{
	
	// Déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int id;
	private String nom;
	private byte[] photo;
	private String description;
	
	//Transformation de l'association UML en Java 
	@OneToMany(mappedBy="categorie", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Produit> listeProduits;

	
	//Déclaration des constructeurs 
	public Categorie() {
		super();
	}
	public Categorie(int id, String nom, byte[] photo, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.photo = photo;
		this.description = description;
	}
	
	//Déclaration des getters et des setters 
	
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
	
	//Méthode toString
	@Override
	public String toString() {
		return "Categorie [idCategorie=" + id + ", nomCategorie=" + nom + ", photo="
				+ Arrays.toString(photo) + ", description=" + description + "]";
	}
	
	

}
