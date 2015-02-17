package ipint.glp.donnees;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author duhaupas
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "titre",
		"dateDebut" }))
public class Evenement extends Publication implements Comparable<Evenement> {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	private Date heureDebut;

	private String lieu;
	private String description;

	public Evenement() {
		super();
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

	@Override
	public int compareTo(Evenement evenement) {
		return this.getTimeStampDebut()
				.compareTo(evenement.getTimeStampDebut());
	}
}
