package ipint.glp.donnees;

import java.sql.Date;

public class Evenement {

	private String titre;
	private Date date;
	private String lieu;
	private String description;
	
	public Evenement(){
		
	}
	public Evenement(String titre, Date date, String lieu, String description){
		this.titre= titre;
		this.date= date;
		this.lieu= lieu;
		this.description= description;
	
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
}
