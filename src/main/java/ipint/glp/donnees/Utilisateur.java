package ipint.glp.donnees;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {
	
	@Id
	private String login;
	
	private String nom;
	private String prenom;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Droit droit;

	
	@OneToMany(mappedBy="utilisateur", targetEntity=Annonce.class)
	private List<Annonce> lesAnnonces = new ArrayList<Annonce>();
	
	
	public Utilisateur(){
		
	}
	
	public Utilisateur(String login, String prenom, String nom, String email, Droit droit) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.droit = droit;
	}
	
	public String getId() {
		return login;
	}

	public void setId(String id) {
		login = (String)id;
	}

	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
