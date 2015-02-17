package ipint.glp.donnees;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
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
	
	@ElementCollection(targetClass = Droit.class) 
	@Enumerated(EnumType.STRING)
	private Set<Droit> droits;

	
	@OneToMany(mappedBy="utilisateur", targetEntity=Annonce.class)
	private List<Annonce> lesAnnonces = new ArrayList<Annonce>();
	
	@OneToMany(mappedBy="utilisateur", targetEntity=Evenement.class)
	private List<Evenement> lesEvenements = new ArrayList<Evenement>();
	
	@OneToMany(mappedBy="utilisateur", targetEntity=Job.class)
	private List<Job> lesJobs = new ArrayList<Job>();
	
	public Utilisateur(){}
	
	public Utilisateur(String login, String prenom, String nom, String email) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.droits = new HashSet<Droit>();
	}
	
	public Utilisateur(String login, String prenom, String nom, String email, Set<Droit> droits) {
		super();
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.droits = droits;
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

	/**
	 * @return the droits
	 */
	public Set<Droit> getDroits() {
		return droits;
	}

	/**
	 * @param droits the droits to set
	 */
	public void setDroits(Set<Droit> droits) {
		this.droits = droits;
	}

	/**
	 * @param lesAnnonces the lesAnnonces to set
	 */
	public void setLesAnnonces(List<Annonce> lesAnnonces) {
		this.lesAnnonces = lesAnnonces;
	}

	/**
	 * @return the lesEvenements
	 */
	public List<Evenement> getLesEvenements() {
		return lesEvenements;
	}

	/**
	 * @param lesEvenements the lesEvenements to set
	 */
	public void setLesEvenements(List<Evenement> lesEvenements) {
		this.lesEvenements = lesEvenements;
	}

	/**
	 * @return the lesJobs
	 */
	public List<Job> getLesJobs() {
		return lesJobs;
	}

	/**
	 * @param lesJobs the lesJobs to set
	 */
	public void setLesJobs(List<Job> lesJobs) {
		this.lesJobs = lesJobs;
	}

}
