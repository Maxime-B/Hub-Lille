package ipint.glp.donnees;

import ipint.glp.interfaces.Publication;

import java.util.ArrayList;

public class Utilisateur {
	private int id;
	private String nom, prenom, email;
	private Droit droit;
	private ArrayList<Publication> lesPublications = new ArrayList<Publication>();
	
	
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

	public ArrayList<Publication> getLesPublications() {
		return lesPublications;
	}

	public void setLesPublications(ArrayList<Publication> lesPublications) {
		this.lesPublications = lesPublications;
	}
	
	public void addPublication(Publication publication){
		lesPublications.add(publication);
	}
	
	public void removePublication(Publication publication){
		lesPublications.remove(publication);
	}
	
	
}
