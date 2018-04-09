package fr.adaming.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "commandes")

public class Commande implements Serializable {

	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_com")
	private long id;
	@Temporal(TemporalType.DATE)
	private Date date;

	// Transfo assos avec ligne de commande
	@OneToMany(mappedBy = "commande")
	private List<LigneCommande> listeCl;

	// Transfo assos avec client
	@ManyToOne
	@JoinColumn(name = "cl_id", referencedColumnName = "id_cl")
	private Client client;

	// Transfo assos avec agent
	@ManyToOne
	@JoinColumn(name = "admin_id", referencedColumnName = "id_admin")
	private Administrateur admin;

	// Constructeurs
	public Commande() {
		super();
	}

	public Commande(Date date) {
		super();
		this.date = date;
	}

	public Commande(long id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}

	// Getter et setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<LigneCommande> getListeCl() {
		return listeCl;
	}

	public void setListeCl(List<LigneCommande> listeCl) {
		this.listeCl = listeCl;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}

}
