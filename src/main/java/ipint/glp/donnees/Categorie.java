package ipint.glp.donnees;

import java.util.ArrayList;

public class Categorie {
	String nom;
	ArrayList<Champs> champs = new ArrayList<Champs>();
	
	public Categorie(String nom, ArrayList<Champs> champs) {
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

	public ArrayList<Champs> getChamps() {
		return champs;
	}

	public void setChamps(ArrayList<Champs> champs) {
		this.champs = champs;
	}
	
	
}
