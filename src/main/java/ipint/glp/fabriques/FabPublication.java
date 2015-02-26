package ipint.glp.fabriques;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Evenement_;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.Publication;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connexion.Connexion;

public class FabPublication {

	
	static FabPublication fb;
	private HashMap<Integer,Annonce> lesAnnonces;
	private HashMap<Integer,Job> lesJobs;


	private Connexion connexion;
	private EntityManager em = Connexion.getConnexion().getEm();


	
	private FabPublication(){
		connexion = Connexion.getConnexion();

	}
	
	public static FabPublication getInstance(){
		if(fb == null){
			fb = new FabPublication();
		}
		return fb;
	}
	
	public List<Publication> listerPublications(){
		Query query = connexion.getEm().createQuery("Select pub from Publication pub");
		return  query.getResultList();
	}
	
	public List<Annonce> listerPubAnnonces (){

		Query query = connexion.getEm().createQuery("Select ann from Annonce ann");
		List<Annonce> annonces = query.getResultList();
		lesAnnonces.clear();
		for(Annonce a : annonces){
			lesAnnonces.put(a.getId(), a);
		}
		return annonces;
		
	}
	
	public List<Job> listerPubJobs (){

		Query query = connexion.getEm().createQuery("Select jb from Job jb");
		List<Job> jobs = query.getResultList();
		lesJobs.clear();
		for(Job a : jobs){
			lesJobs.put(a.getId(), a);
		}
		return jobs;
		
	}
	
	public List<Evenement> listerPubEvenements() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Evenement> cq = cb.createQuery(Evenement.class);
		Root<Evenement> root = cq.from(Evenement.class);

		return em.createQuery(
				cq.select(root)
				.where(cb.greaterThan(root.get(Evenement_.dateDebut), cb.currentDate()))
				.orderBy(cb.asc(root.get(Evenement_.dateDebut)), cb.asc(root.get(Evenement_.heureDebut)))
			)
			.setMaxResults(50)
			.getResultList();
	}
	
	public List<Publication> listerPubMotCle(String motCle){
		Query query = connexion.getEm().createQuery("Select pub from Publication pub where LOWER(pub.titre) like LOWER(:motCle)");
		query.setParameter("motCle", "%"+motCle+"%");
		
		//Query query = connexion.getEm().createQuery("Select Job.id from Job where Job.titre ='mot'");
		return query.getResultList();
	}

}