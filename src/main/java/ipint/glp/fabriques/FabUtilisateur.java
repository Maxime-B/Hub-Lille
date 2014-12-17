package ipint.glp.fabriques;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import connexion.Connexion;

public class FabUtilisateur {
	
	static FabUtilisateur fu;
	private HashMap<Integer,Utilisateur> lesUtilisateurs;
	private Connexion connexion;
	
	private FabUtilisateur(){
		lesUtilisateurs = new HashMap<Integer,Utilisateur>();
		connexion = Connexion.getConnexion();
	}
	
	public static FabUtilisateur getInstance(){
		if(fu == null){
			fu = new FabUtilisateur();
		}
		return fu;
	}
	
	public Utilisateur creerUtilisateur(String nom, String prenom, String email, Droit droit){
		this.listerUtilisateurs();
		Utilisateur u = new Utilisateur();
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		u.setDroit(droit);
		connexion.getEm().persist(u);
		lesUtilisateurs.put(u.getId(), u);
		return u;
	}
	
	public List<Utilisateur> listerUtilisateurs(){
		Query query = connexion.getEm().createQuery("Select ut from Utilisateur ut");
		List<Utilisateur> utilisateurs = query.getResultList();
		lesUtilisateurs.clear();
		for(Utilisateur u : utilisateurs){
			lesUtilisateurs.put(u.getId(), u);
		}
		return utilisateurs;
	}
	
}
