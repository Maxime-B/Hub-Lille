package ipint.glp.fabriques;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Annonce_;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connexion.Connexion;

public class FabAnnonce {


static FabAnnonce fb;
	private HashMap<Integer,Annonce> lesAnnonces;
	private Connexion connexion;
	private HashMap<String, TypeAnnonce> lesTypes = new HashMap<String,TypeAnnonce>();

	
	private FabAnnonce(){
		lesAnnonces = new HashMap<Integer,Annonce>();
		connexion = Connexion.getConnexion();

	}
	
	public static FabAnnonce getInstance(){
		if(fb == null){
			fb = new FabAnnonce();
		}
		return fb;
	}
	
//	public Annonce creerAnnonce(Categorie categorie,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
//		this.listerAnnonces();
//		Annonce a = new Annonce();
//		a.setCategorie(categorie);
//		
//		a.setType(typeAnnonce);
//		a.setUtilisateur(utilisateur);
//		a.setLesChamps(lesChamps);
//		connexion.getEm().persist(a);
//		utilisateur.addAnnonce(a);
//		lesAnnonces.put(a.getId(),a);
//		categorie.addAnnonce(a);
//		return a;
//	}
	public Annonce creerAnnonce(Categorie categorie,String titre, String description,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		this.listerAnnonces(typeAnnonce);
		Annonce a = new Annonce();
		a.setCategorie(categorie);
		a.setDescription(description);
		a.setTitre(titre);
		a.setType(typeAnnonce);
		a.setUtilisateur(utilisateur);
		a.setLesChamps(lesChamps);
		a.setSignal(0);
		Calendar cal = new GregorianCalendar();
		a.setDatepublication(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DAY_OF_MONTH, 30);
		a.setFinpublication(new Date(cal.getTimeInMillis()));
		connexion.getTx().begin();
		connexion.getEm().persist(a);
		connexion.getEm().flush();
		connexion.getTx().commit();
		utilisateur.addAnnonce(a);
		lesAnnonces.put(a.getId(),a);
		categorie.addAnnonce(a);
		return a;
	}


	public List<Annonce> listerAnnonces (TypeAnnonce typeAnnonce){

		Query query = connexion.getEm().createQuery("Select ann from Annonce ann");
		List<Annonce> annonces = query.getResultList();
		lesAnnonces.clear();
		for(Annonce a : annonces){
			lesAnnonces.put(a.getId(), a);
		}
		return annonces;
		
	}
	
	public List<Annonce> listerAnnoncesParSignalement() {
		EntityManager em = connexion.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
		Root<Annonce> root = cq.from(Annonce.class);

		return em.createQuery(
				cq.select(root)
				.where(cb.greaterThan(root.get(Annonce_.nbSignalement), 5))
				.orderBy(cb.asc(root.get(Annonce_.nbSignalement)))
			)
			.getResultList();
	}
	
	public List<Annonce> getAnnoncesPerimees() {
		EntityManager em = connexion.getEm();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Annonce> cq = cb.createQuery(Annonce.class);
		Root<Annonce> root = cq.from(Annonce.class);
		
		return em.createQuery(
			cq.where(cb.lessThan(root.get(Annonce_.finpublication), cb.currentDate()))
		).getResultList();
	}
	
	public void supprimerAnnonce(Annonce a){
		connexion.getTx().begin();
		//String query ="Delete from Annonce where Annonce.id =a.id";
		System.out.println(a.getId());
		a.getUtilisateur().getLesAnnonces().remove(a);
		a.getCategorie().getLesAnnonces().remove(a);
		connexion.getEm().remove(a);
		connexion.getTx().commit();
		
		


	}
	public Annonce signalerAnnonce(Annonce a){
		connexion.getTx().begin();
		a.setSignal(a.getSignal()+1);
		connexion.getEm().persist(a);
		connexion.getEm().flush();
		connexion.getTx().commit();
		return a;
	}

	public List<Annonce> listerAnnoncesParCategorie(Categorie categorie){

		return categorie.getLesAnnonces();


	}
	public Annonce modifier(Annonce a)
	{
		connexion.getTx().begin();
		connexion.getEm().merge(a);
		connexion.getTx().commit();
		return a;
	}
	
	public TypeAnnonce getTypeAnnonce(String type){
		return lesTypes.get(type);
	}
	public void supprimerAnnonce(){

		connexion.getTx().begin();
		String query ="Delete from Annonce";
		connexion.getEm().createQuery(query).executeUpdate();
		System.out.println("Tout est supprimer...");
		connexion.getTx().commit();



	}

	
	
	
	public Annonce rechercherParId(int reference) {
		// TODO Auto-generated method stub
		String query ="select a from Annonce a where a.id ="+reference;
		ArrayList<Annonce> l = (ArrayList<Annonce>) connexion.getEm().createQuery(query).getResultList();
		if (l.isEmpty()) {
			return null;
		}
		return l.get(0);
	}
	
	public List<Annonce> chercherAnnonceParMotCle(String motCle){
		Query query = connexion.getEm().createQuery("Select ann from Annonce ann where LOWER(ann.titre) like LOWER(:motCle) or LOWER(ann.description) like LOWER(:motCle)");
		query.setParameter("motCle", "%"+motCle+"%");
		
		//Query query = connexion.getEm().createQuery("Select Job.id from Job where Job.titre ='mot'");
		List<Annonce> anns = query.getResultList();
		lesAnnonces.clear();
		for(Annonce a : anns){
			lesAnnonces.put(a.getId(), a);
		}
		return anns;
	}
	public List<Annonce> chercherAnnonceParMotCleCate(String motCle,Categorie categorie){
	/*	Query query = connexion.getEm().createQuery("Select ann from Annonce ann where LOWER(ann.titre) like LOWER(:motCle) or LOWER(ann.description) like LOWER(:motCle) and ann.categorie= :categorie)");
	query.setParameter("motCle", "%"+motCle+"%");
		query.setParameter("categorie", categorie.getNom());*/
		//Query query = connexion.getEm().createQuery("Select Job.id from Job where Job.titre ='mot'");
		//List<Annonce> anns = query.getResultList();
		List<Annonce> anns = categorie.getLesAnnonces();
		System.out.println(anns.size());
		List<Annonce> annsFinal = new ArrayList();
		lesAnnonces.clear();
		for(Annonce a : anns){
			//lesAnnonces.put(a.getId(), a);
			System.out.println("**********************");
			System.out.println(a.getTitre().indexOf(motCle));
			if ((a.getTitre().indexOf(motCle.toLowerCase())>=0) || (a.getDescription().indexOf(motCle.toLowerCase())>=0) || (a.getTitre().indexOf(motCle.toUpperCase())>=0) || (a.getDescription().indexOf(motCle.toUpperCase())>=0))
				//if ((a.getTitre()==motCle) || (a.getDescription()==motCle))
				annsFinal.add(a);
		}
		System.out.println(annsFinal.size());
		return annsFinal;
		
	}
	public List<Annonce> chercherAnnoncesParType(TypeAnnonce type){
		Query query = connexion.getEm().createQuery("Select ann from Annonce ann where ann.type = :type");
		query.setParameter("type", type);
		List<Annonce> annonces = query.getResultList();
		lesAnnonces.clear();
		for(Annonce a : annonces){
			lesAnnonces.put(a.getId(), a);
		}
		return annonces;
	}
	

	public List<Annonce> chercherAnnoncesParTypeCate(TypeAnnonce type, Categorie categorie){
		List<Annonce> anns = categorie.getLesAnnonces();
		System.out.println(anns.size());
		List<Annonce> annsFinal = new ArrayList();
		lesAnnonces.clear();
		for(Annonce a : anns){
			//lesAnnonces.put(a.getId(), a);
			System.out.println("**********************");
			if(a.getType() == type && a.getCategorie() == categorie)
				//if ((a.getTitre()==motCle) || (a.getDescription()==motCle))
				annsFinal.add(a);
		}
		System.out.println(annsFinal.size());
		return annsFinal;
	}
	public List<Annonce> chercherAnnoncesParTypeMotCle(TypeAnnonce type, String motCle){
		//Select ann from Annonce ann where LOWER(ann.titre) like LOWER(:motCle) or LOWER(ann.description) like LOWER(:motCle)
		Query query = connexion.getEm().createQuery("Select ann from Annonce ann where ann.type = :type ");
		query.setParameter("type", type);
		System.out.println(motCle);
		List<Annonce> anns = query.getResultList();
		lesAnnonces.clear();
			System.out.println(anns.size());
			List<Annonce> annsFinal = new ArrayList();
			lesAnnonces.clear();
			for(Annonce a : anns){
				//lesAnnonces.put(a.getId(), a);
				System.out.println("**********************");
				System.out.println(a.getTitre().indexOf(motCle));
				if (a.getType() == type && (a.getTitre().indexOf(motCle.toLowerCase())>=0) || (a.getDescription().indexOf(motCle.toLowerCase())>=0) || (a.getTitre().indexOf(motCle.toUpperCase())>=0) || (a.getDescription().indexOf(motCle.toUpperCase())>=0))
					annsFinal.add(a);
			}
			System.out.println(annsFinal.size());
			return annsFinal;
	}

	public List<Annonce> chercherAnnoncesParTypeMotCleCate(TypeAnnonce type, Categorie categorie, String motCle){
		Query query = connexion.getEm().createQuery("Select ann from Annonce ann where ann.type = :type and ann.categorie= :categorie");
		query.setParameter("type", type);
		query.setParameter("categorie", categorie);
		List<Annonce> anns = query.getResultList();
		lesAnnonces.clear();
		System.out.println(anns.size());
		List<Annonce> annsFinal = new ArrayList();
		lesAnnonces.clear();
		for(Annonce a : anns){
			//lesAnnonces.put(a.getId(), a);
			System.out.println("**********************");
			System.out.println(a.getTitre().indexOf(motCle));
			if ((a.getType() == type) && ((a.getCategorie() == categorie) && (a.getTitre().indexOf(motCle.toLowerCase())>=0))  || (a.getDescription().indexOf(motCle.toLowerCase())>=0) || (a.getTitre().indexOf(motCle.toUpperCase())>=0) || (a.getDescription().indexOf(motCle.toLowerCase())>=0))
				annsFinal.add(a);
		}
		System.out.println(annsFinal.size());
		return annsFinal;
	}
	

}
