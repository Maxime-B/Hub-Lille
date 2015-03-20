package ipint.glp.donnees;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Annonce extends Publication{

	
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	private Categorie categorie;
 
	@CollectionTable(name="libelle", joinColumns=@javax.persistence.JoinColumn(name="valeur"))
	private HashMap<String, String> lesChamps = new HashMap<String, String>();
	
	@Enumerated(EnumType.STRING)
	private TypeAnnonce type;
	
	
	private String description;
	private ArrayList<String> images;
	private int nbSignalement;
	private Date datepublication;
	private Date finpublication;
	
	
	
	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Annonce() {
		super();
		
	}

	public Annonce(Categorie categorie, Utilisateur utilisateur,TypeAnnonce type) {
		super();
		this.categorie = categorie;
	
		this.type = type;
		initializeLesChamps();
	}
	
	public TypeAnnonce getType() {
		return type;
	}

	public void setType(TypeAnnonce type) {
		this.type = type;
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



	public int getSignal() {
		return nbSignalement;
	}

	public void setSignal(int signal) {
		this.nbSignalement = signal;
	}

	public Date getDatepublication() {
		return datepublication;
	}

	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
	}

	public Date getFinpublication() {
		return finpublication;
	}

	public void setFinpublication(Date finpublication) {
		this.finpublication = finpublication;
	}






	

}
