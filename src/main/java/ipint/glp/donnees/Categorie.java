package ipint.glp.donnees;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categorie {
	
	@Id
	private String nom;

	private List<Champ> champs = new ArrayList<Champ>();
	
	public Categorie(){
		super();
	}
	
	public Categorie(String nom, List<Champ> champs) {
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

	public List<Champ> getChamps() {
		return champs;
	}

	public void setChamps(List<Champ> champs) {
		this.champs = champs;
	}
	
	
}
