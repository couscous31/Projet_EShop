package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;

@Repository

public class ClientDaoImpl implements IClientDao {

	@Autowired
	SessionFactory sf;

	Session s;

	// Setter de SessionFactory
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	// Méthodes

	@Override
	public List<Client> getAllClient() {
		// req HQL
		String req = "FROM Client AS cl";

		// ouvrir la session
		s = sf.getCurrentSession();

		// récup du query
		Query q = s.createQuery(req);

		return q.list();
	}

	@Override
	public Client addClient(Client cl) {
		// ouvrir la session
		s = sf.getCurrentSession();
		// méthode save d'hibernate
		s.save(cl);

		return cl;
	}

	@Override
	public int updateClient(Client cl) {
		// req HQL
		String req = "UPDATE Client AS cl SET cl.nom=:pNom," 
									+ "cl.prenom=:pPre," 
									+ "cl.adresse=:pAd,"
									+ "cl.mail=:pMail," 
									+ "cl.mdp=:pMdp," 
									+ "cl.tel=:pTel " 
									+ "WHERE cl.id=:pId";
		// ouvrir la session
		s = sf.getCurrentSession();

		// récup du query
		Query q = s.createQuery(req);

		// passage des params
		q.setParameter("pNom", cl.getNom());
		q.setParameter("pPre", cl.getPrenom());
		q.setParameter("pAd", cl.getAdresse());
		q.setParameter("pMail", cl.getMail());
		q.setParameter("pMdp", cl.getMdp());
		q.setParameter("pTel", cl.getTel());
		q.setParameter("pId", cl.getId());

		return q.executeUpdate();
	}

	@Override
	public int deleteClient(Client cl) {
		// req HQL
		String req = "DELETE FROM Client AS cl WHERE cl.id=:pId";

		// ouvrir la session
		s = sf.getCurrentSession();

		// récup du query
		Query q = s.createQuery(req);

		// passage des params
		q.setParameter("pId", cl.getId());

		return q.executeUpdate();
	}

	@Override
	public Client isExist(Client cl) {
		// req HQL
		String req = "FROM Client AS cl WHERE cl.mail=:pMail AND cl.mdp=:pMdp";

		// ouvrir une session
		s = sf.getCurrentSession();

		// récup du query
		Query q = s.createQuery(req);

		// passage des params
		q.setParameter("pMail", cl.getMail());
		q.setParameter("pMdp", cl.getMdp());

		return (Client) q.uniqueResult();
	}

}
