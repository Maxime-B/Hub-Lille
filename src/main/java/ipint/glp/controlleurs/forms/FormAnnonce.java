package ipint.glp.controlleurs.forms;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.metiers.MetierCategorie;

import java.util.HashMap;

public class FormAnnonce {
	private int id;
	private String categorie;
	private Categorie categorieObject;
	/**
	 * @return the categorieObject
	 */
	public Categorie getCategorieObject() {
		return categorieObject;
	}

	/**
	 * @param categorieObject the categorieObject to set
	 */
	public void setCategorieObject(Categorie categorieObject) {
		this.categorieObject = categorieObject;
		if (categorieObject != null) {
			this.categorie = categorieObject.getNom();
		}
	}

	private HashMap<String, String> lesChamps;
	private TypeAnnonce type;
	
	public FormAnnonce() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
		categorieObject = new MetierCategorie().getCategorie(categorie);
	}

	/**
	 * @return the lesChamps
	 */
	public HashMap<String, String> getLesChamps() {
		return lesChamps;
	}

	/**
	 * @param lesChamps the lesChamps to set
	 */
	public void setLesChamps(HashMap<String, String> lesChamps) {
		this.lesChamps = lesChamps;
	}

	/**
	 * @return the type
	 */
	public TypeAnnonce getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeAnnonce type) {
		this.type = type;
	}
	
	
}
