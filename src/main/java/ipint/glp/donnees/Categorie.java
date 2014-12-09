package ipint.glp.donnees;

import java.util.ArrayList;

public class Categorie {
	private String nom;
	private ArrayList<Champ> champs = new ArrayList<Champ>();
	
	public Categorie(){
		super();
	}
	
	public Categorie(String nom, ArrayList<Champ> champs) {
		super();
		this.nom = nom;
		this.champs = champs;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Champ> getChamps() {
		return champs;
	}

	public void setChamps(ArrayList<Champ> champs) {
		this.champs = champs;
	}
	
	
}
