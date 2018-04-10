package fr.adaming.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void envoyerMessage(String mailRecup) {

		// Réalisation de la connexion
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

			// Set from : adresse du destinataire
			msg.setFrom(new InternetAddress(username));

			// Set to : adresse du réceptionniste
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailRecup));

			// Set subject : objet du message
			msg.setSubject("Validation de votre commande");

			// Corps du message
			msg.setContent(
					"<h1>Bonjour, votre commande a bien été validée. <br/> Vous la recevrez dans les plus brefs délais ; <br/>. Cordialement</h1>",
					"text/html");

			// Envoi du message
			Transport.send(msg, msg.getAllRecipients());

			System.out.println("L'envoi du message est un succès !");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
