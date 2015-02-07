package ipint.glp.controlleurs.forms;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.metiers.MetierCategorie;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class FormAnnonce {
	private static final Logger logger = LoggerFactory
			.getLogger(FormAnnonce.class);
	
	private int id;
	private String categorie;
	private Categorie categorieObject;
	private TypeAnnonce type;
	private String titre, description;
	
	private HashMap<String, String> texte;
	private HashMap<String, String> textarea;
	
	//@NumberFormat
	private HashMap<String, Double> numerique;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")//yyyy-MM-dd
	@Temporal(TemporalType.DATE)
	private HashMap<String, Date> date;
	
	private HashMap<String, File> image;
	private ArrayList<HashMap<String, ?>> lesTypes;
	
	public FormAnnonce() {
		super();
		lesTypes = new ArrayList<HashMap<String, ?>>();
		
		texte = new HashMap<String, String>();
		lesTypes.add(texte);
		
		textarea = new HashMap<String, String>();
		lesTypes.add(textarea);
		
		numerique = new HashMap<String, Double>();
		lesTypes.add(numerique);
		
		date = new HashMap<String, Date>();
		lesTypes.add(date);
		
		image = new HashMap<String, File>();
		lesTypes.add(image);
	}
	
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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


	/**
	 * @return the texte
	 */
	public HashMap<String, String> getTexte() {
		return texte;
	}


	/**
	 * @param texte the texte to set
	 */
	public void setTexte(HashMap<String, String> texte) {
		this.texte = texte;
	}


	/**
	 * @return the textarea
	 */
	public HashMap<String, String> getTextarea() {
		return textarea;
	}


	/**
	 * @param textarea the textarea to set
	 */
	public void setTextarea(HashMap<String, String> textarea) {
		this.textarea = textarea;
	}


	/**
	 * @return the numerique
	 */
	public HashMap<String, Double> getNumerique() {
		return numerique;
	}


	/**
	 * @param numerique the numerique to set
	 */
	public void setNumerique(HashMap<String, Double> numerique) {
		this.numerique = numerique;
	}


	/**
	 * @return the date
	 */
	public HashMap<String, Date> getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(HashMap<String, Date> date) {
		this.date = date;
	}


	/**
	 * @return the image
	 */
	public HashMap<String, File> getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(HashMap<String, File> image) {
		this.image = image;
	}


	/**
	 * @return the lesTypes
	 */
	public ArrayList<HashMap<String, ?>> getLesTypes() {
		return lesTypes;
	}


	/**
	 * @param lesTypes the lesTypes to set
	 */
	public void setLesTypes(ArrayList<HashMap<String, ?>> lesTypes) {
		this.lesTypes = lesTypes;
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
		HashMap<String, String> lesChamps = new HashMap<String, String>();
		for (HashMap<String, ?> type : lesTypes) {
			for (Entry<String, ?> entry : type.entrySet()) {
				String stringOfValue;
				if (entry.getValue() == null) {
					stringOfValue = "";
				}else if (entry.getValue() instanceof Date) {
					Date date = (Date) entry.getValue(); 
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					stringOfValue = simpleDateFormat.format(date);
				} else {
					stringOfValue = entry.getValue().toString();
				}
				lesChamps.put((String)entry.getKey(), stringOfValue);
			}
		}
		return lesChamps;
	}
}
