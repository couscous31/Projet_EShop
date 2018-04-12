package fr.adaming.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Service("commandeService")
@Transactional

public class CommandeServiceImpl implements ICommandeService {

	@Autowired
	private ICommandeDao comDao;

	// le setter
	public void setComDao(ICommandeDao comDao) {
		this.comDao = comDao;
	}

	@Override
	public List<Commande> getAllCom(Client cl) {
		return comDao.getAllCom(cl);
	}

	@Override
	public Commande addCom(Commande com, Client cl) {
		com.setClient(cl);
		return comDao.addCom(com);
	}

	@Override
	public int deleteCom(Commande com) {
		return comDao.deleteCom(com);
	}

	@Override
	public String genererPDF(Commande com, double montant) {
		
		//récup la liste des lignes de com
		List<LigneCommande> listeLc = com.getListeCl();
		
		Document document = new Document(PageSize.A4, 75, 75, 75, 75);
		String cheminPDF = "C:\\Users\\Public\\Commande_"+com.getId()+".pdf";
		
		try{
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(cheminPDF));
			document.open();
			
			Paragraph titre = new Paragraph("Puer des pieds !", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.UNDERLINE, new CMYKColor(0,100,0,0)));
			titre.setSpacingAfter(20);
			document.add(titre);
			
			
			Paragraph idCommande = new Paragraph("Commande "+String.valueOf(com.getId())+" datée du : "+com.getDate(), FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE,16));
			idCommande.setSpacingAfter(20);
			document.add(idCommande);
			
			
			Paragraph idClient = new Paragraph("Le client : ", FontFactory.getFont(FontFactory.HELVETICA,14));
			idClient.setSpacingAfter(5);
			document.add(idClient);
			
			Paragraph nom = new Paragraph("Mme/Mr. "+com.getClient().getNom()+" "+com.getClient().getPrenom(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			nom.setSpacingAfter(2);
			document.add(nom);

			Paragraph adresse = new Paragraph(com.getClient().getAdresse(), FontFactory.getFont(FontFactory.HELVETICA, 12))
			adresse.setSpacingAfter(2);
			document.add(adresse);

			Paragraph tel = new Paragraph(com.getClient().getTel(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			tel.setSpacingAfter(2);
			document.add(tel);

			Paragraph mail = new Paragraph(com.getClient().getMail(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			mail.setSpacingAfter(20);
			document.add(mail);
			
			
			Paragraph articlest=new Paragraph("Liste des articles commandés:", FontFactory.getFont(FontFactory.HELVETICA,14));
			articlest.setSpacingAfter(5);
			document.add(articlest);

			

			PdfPTable listeArticles = new PdfPTable(5);
			
			listeArticles.setSpacingAfter(10);
			listeArticles.addCell("Modèle");
			listeArticles.addCell("Description");
			listeArticles.addCell("Prix de l'article");
			listeArticles.addCell("Quantite");
			listeArticles.addCell("Montant");

			for(LigneCommande lc : listeLc){

				listeArticles.addCell(lc.getProduit().getDesignation());
				listeArticles.addCell(lc.getProduit().getDescription());
				listeArticles.addCell(String.valueOf(lc.getProduit().getPrix()));
				listeArticles.addCell(String.valueOf(lc.getQuantite()));
				listeArticles.addCell(String.valueOf(lc.getPrix()));

			}

			document.add(listeArticles);
			
			
			Paragraph total = new Paragraph("Le montant total de la commande est de : "+String.valueOf(montant)+" €", FontFactory.getFont(FontFactory.HELVETICA, 12));
			total.setSpacingAfter(10);
			document.add(total);

			return cheminPDF;
			
		}catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return null;

		}finally {
			document.close();

		}
	
	}

}
