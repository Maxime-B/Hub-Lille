package ipint.glp.donnees;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	
	@Id
	private String nom;

	private List<Champ> champs = new ArrayList<Champ>();
	
	@OneToMany(cascade= CascadeType.PERSIST)
	private List<Annonce> lesAnnonces = new ArrayList<Annonce>();
	
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
	
	public void addAnnonce(Annonce annonce){
		lesAnnonces.add(annonce);
	}
	
	public void removeAnnonce(Annonce annonce){
		lesAnnonces.remove(annonce);
	}

	public List<Annonce> getLesAnnonces() {
		return lesAnnonces;
	}

	public void setLesAnnonces(List<Annonce> lesAnnonces) {
		this.lesAnnonces = lesAnnonces;
	}
	
	
	
	
}
