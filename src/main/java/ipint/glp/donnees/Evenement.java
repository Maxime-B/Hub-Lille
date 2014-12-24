package ipint.glp.donnees;

import ipint.glp.interfaces.Publication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * "titre" est utilisé comme id
 * @author duhaupas
 */
@Entity
public class Evenement implements Publication {
	@Id
	private String titre;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@DateTimeFormat(pattern = "hh:mm")
	@Temporal(TemporalType.TIME)
	private Date heureDebut;

	private String lieu;
	private String description;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Utilisateur utilisateur;

	public Evenement() {
		super();
	}

	public Evenement(String titre, String lieu, String description,
			String duree, Utilisateur utilisateur) {
		super();
		this.titre = titre;
		this.lieu = lieu;
		this.description = description;
		this.utilisateur = utilisateur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getTimeStampDebut() {
		if (dateDebut == null) {
			return null;
		}
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(dateDebut);
		if (heureDebut != null) {
			GregorianCalendar heure = new GregorianCalendar();
			heure.setTime(heureDebut);
			date.set(Calendar.HOUR, heure.get(Calendar.HOUR));
			date.set(Calendar.MINUTE, heure.get(Calendar.MINUTE));
		}
		return date.getTime();
	}
	public void setTimeStampDebut(Date timeStamp) {
		this.dateDebut = timeStamp;
		this.heureDebut = timeStamp;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date date) throws ParseException {
		dateDebut = date;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date date) throws ParseException {
		heureDebut = date;
	}
}
