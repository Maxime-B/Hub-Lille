package ipint.glp.fabriques;

import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.donnees.Utilisateur_;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connexion.Connexion;

public class FabUtilisateur {
	
	private static FabUtilisateur fu;
	private Connexion connexion;
	private EntityManager em;
	
	private FabUtilisateur(){
		connexion = Connexion.getConnexion();
		em = connexion.getEm();
	}
	
	public static FabUtilisateur getInstance(){
		if(fu == null){
			fu = new FabUtilisateur();
		}
		return fu;
	}
	
	public Utilisateur creer(String login, String nom, String prenom, String email, Set<Droit> droits){
		this.listerUtilisateurs();
		Utilisateur u = new Utilisateur();
		u.setLogin(login);
		u.setNom(nom);
		u.setPrenom(prenom);
		u.setEmail(email);
		u.setDroits(droits);
		connexion.getEm().persist(u);
		return u;
	}
	
	public List<Utilisateur> listerUtilisateurs(){
		Query query = connexion.getEm().createQuery("Select ut from Utilisateur ut");
		List<Utilisateur> utilisateurs = query.getResultList();
		return utilisateurs;
	}
	
	public List<Utilisateur> listerParRole() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
		Root<Utilisateur> root = cq.from(Utilisateur.class);
		HashSet<Droit> droitsParDefaut = new HashSet<Droit>();
		droitsParDefaut.add(Droit.DEFAUT);
		
		return em.createQuery(
			cq.select(root)
			.where(cb.notEqual(root.get(Utilisateur_.droits), droitsParDefaut))
			.orderBy(cb.asc(root.get(Utilisateur_.login)))
			.distinct(true)
		)
		//.setMaxResults(50)
		.getResultList();
	}
	
	public Utilisateur obtenir(String login) {
		return em.find(Utilisateur.class, login);
	}
	
	public void supprimer(Utilisateur o) {
		em.remove(o);
	}

	public Utilisateur modifier(Utilisateur o) {
		em.persist(o);
		//em.merge(o);
		return o;
	}
	
}
