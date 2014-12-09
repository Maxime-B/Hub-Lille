package ipint.glp.donnees;

import ipint.glp.interfaces.Publication;

public class Temoignage implements Publication {

	private String titre;
	private Continent continent;
	private String description;
	private Utilisateur utilisateur;
	
	public Temoignage(){
		super();
	}
	public Temoignage(String titre, Continent continent, String description,Utilisateur utilisateur){
		this.titre=titre;
		this.continent=continent;
		this.description = description;
		this.utilisateur = utilisateur;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Continent getContinent() {
		return continent;
	}
	public void setContinent(Continent continent) {
		this.continent = continent;
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
}
