package ipint.glp.donnees;

import java.util.HashMap;

import ipint.glp.interfaces.Publication;

public class Annonce implements Publication {

	private int id;
	private String titre;
	private String categories;
	private HashMap<String, String> lesChamps = new HashMap();
	private typeAnnonce type ;
	
	
	public typeAnnonce getType() {
		return type;
	}

	public void setType(typeAnnonce type) {
		this.type = type;
	}

	public Annonce() {
		super();
	}

	public Annonce(int id, String titre, String categories,
			HashMap<String, String> lesChamps) {
		super();
		this.id = id;
		this.titre = titre;
		this.categories = categories;
		this.lesChamps = lesChamps;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public HashMap<String, String> getLesChamps() {
		return lesChamps;
	}

	public void setLesChamps(HashMap<String, String> lesChamps) {
		this.lesChamps = lesChamps;
	}

	@Override
	public void publier() {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public void modifier() {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer() {
		// TODO Auto-generated method stub
		
	}

}
