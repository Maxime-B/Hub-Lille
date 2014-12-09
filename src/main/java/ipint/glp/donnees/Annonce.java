package ipint.glp.donnees;

import java.util.HashMap;

import ipint.glp.interfaces.Publication;

public class Annonce implements Publication {

	private int id;
	private Categorie categorie;
	private HashMap<String, String> lesChamps = new HashMap<String, String>();
	private TypeAnnonce type;
	private Utilisateur utilisateur;
	
	
	public Annonce() {
		super();
	}

	public Annonce(int id, Categorie categorie, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.utilisateur = utilisateur;
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
