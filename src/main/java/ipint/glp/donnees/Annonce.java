package ipint.glp.donnees;

import java.util.HashMap;

import ipint.glp.interfaces.Publication;

public class Annonce implements Publication {

	private int id;
	private Categorie categorie;
	private HashMap<String, String> lesChamps = new HashMap<String, String>();
	private TypeAnnonce type;
	
	
	public Annonce() {
		super();
	}

	public Annonce(int id, Categorie categorie) {
		super();
		this.id = id;
		this.categorie = categorie;
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


}
