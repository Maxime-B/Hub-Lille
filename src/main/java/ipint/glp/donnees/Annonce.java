package ipint.glp.donnees;

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


@Entity
public class Annonce {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	private Utilisateur utilisateur;
	
	@ManyToOne(cascade= CascadeType.PERSIST)
	private Categorie categorie;

	@CollectionTable(name="libelle", joinColumns=@javax.persistence.JoinColumn(name="valeur"))
	private HashMap<String, String> lesChamps = new HashMap<String, String>();
	
	@Enumerated(EnumType.STRING)
	private TypeAnnonce type;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
