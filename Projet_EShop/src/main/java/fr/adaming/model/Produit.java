package fr.adaming.model;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="produits")
public class Produit implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_p")
	private int id;
	private String designation;
	private String description;
	private double prix;
	private int quantite;
	@Column(columnDefinition="TINYINT (1)")
	private boolean selectionne;
	@Lob
	private byte[] photoProd;
	@Transient
	private String image;
	
	//Transformation des associations UML en java :
	//Avec categorie:
	@ManyToOne
	@JoinColumn(name="cat_id", referencedColumnName="id_cat")
	private Categorie categorie;
	
	//Avace la ligne de commande :
	@OneToMany(mappedBy="produit")
	private List<LigneCommande> listeLigneCom;
	
	//Avec l'administrateur :
	@ManyToOne
	@JoinColumn(name="a_id", referencedColumnName="id_admin")
	private Administrateur administrateur;

	
	//constructeurs :
	public Produit() {
		super();
		this.categorie=new Categorie();
		
	}


	public Produit(String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photoProd) {
		super();
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photoProd = photoProd;
		
	}


	public Produit(int id, String designation, String description, double prix, int quantite, boolean selectionne,
			byte[] photoProd) {
		super();
		this.id = id;
		this.designation = designation;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photoProd = photoProd;
		
	}


	
	//getters et setters 
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrix() {
		return prix;
	}


	public void setPrix(double prix) {
		this.prix = prix;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public boolean isSelectionne() {
		return selectionne;
	}


	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}


	public byte[] getPhotoProd() {
		return photoProd;
	}


	public void setPhotoProd(byte[] photoProd) {
		this.photoProd = photoProd;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<LigneCommande> getListeLigneCom() {
		return listeLigneCom;
	}


	public void setListeLigneCom(List<LigneCommande> listeLigneCom) {
		this.listeLigneCom = listeLigneCom;
	}


	public Administrateur getAdministrateur() {
		return administrateur;
	}


	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}


	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", description=" + description + ", prix=" + prix
				+ ", quantite=" + quantite + ", selectionne=" + selectionne + ", photoProd="
				+ Arrays.toString(photoProd) + ", image=" + image + ", categorie=" + categorie + ", listeLigneCom="
				+ listeLigneCom + ", administrateur=" + administrateur + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
