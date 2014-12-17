package ipint.glp.donnees;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	private String prenom;
	
	@Column(unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Droit droit;

	
	@OneToMany(mappedBy="utilisateur")
	private List<Annonce> lesAnnonces = new ArrayList<Annonce>();
	
	
	public Utilisateur(){
		
	}
	
	public Utilisateur(int id, String nom, String prenom, String email, Droit droit) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.droit = droit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Droit getDroit() {
		return droit;
	}

	public void setDroit(Droit droit) {
		this.droit = droit;
	}

	public List<Annonce> getLesAnnonces() {
		return lesAnnonces;
	}

	public void setLesAnnonce(List<Annonce> lesAnnonces) {
		this.lesAnnonces = lesAnnonces;
	}
	
	public void addAnnonce(Annonce a){
		lesAnnonces.add(a);
	}
	
	public void removeAnnonce(Annonce a){
		lesAnnonces.remove(a);
	}
	
	
}
