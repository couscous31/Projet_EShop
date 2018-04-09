package fr.adaming.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="produits")
public class Produit implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_p")
	private long id;
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
	private List<LigneCommande> listeLigneCom;
	

}
