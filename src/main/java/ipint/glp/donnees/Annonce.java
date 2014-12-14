package ipint.glp.donnees;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ipint.glp.interfaces.Publication;

@Entity
public class Annonce implements Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Categorie categorie;
	private HashMap<String, String> lesChamps = new HashMap<String, String>();
	private TypeAnnonce type;
	private Utilisateur utilisateur;
	
	
	public Annonce() {
		super();
		
	}

	public Annonce(Categorie categorie, Utilisateur utilisateur,TypeAnnonce type) {
		super();
		this.categorie = categorie;
		this.utilisateur = utilisateur;
		this.type = type;
		initializeLesChamps();
	}
	
	public TypeAnnonce getType() {
		return type;
	}

	public void setType(TypeAnnonce type) {
		this.type = type;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Categorie getCategories() {
		return categorie;
	}

	public void setCategories(Categorie categorie) {
		this.categorie = categorie;
	}

	public HashMap<String, String> getLesChamps() {
		return lesChamps;
	}

	public void setLesChamps(HashMap<String, String> lesChamps) {
		this.lesChamps = lesChamps;
	}
	
	public void initializeLesChamps(){
		for(Champ c : categorie.getChamps()){
			lesChamps.put(c.getLibelle(), "");
		}
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	


}
