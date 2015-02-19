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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import connexion.Connexion;

public class FabAnnonce {


static FabAnnonce fb;
	private HashMap<Integer,Annonce> lesAnnonces;
	private Connexion connexion;
	
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
		this.listerAnnonces();
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
		connexion.getEm().persist(a);
		connexion.getEm().flush();
		utilisateur.addAnnonce(a);
		lesAnnonces.put(a.getId(),a);
		categorie.addAnnonce(a);
		return a;
	}


	public List<Annonce> listerAnnonces (){

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
				.where(cb.greaterThan(root.get(Annonce_.signal), 5))
				.orderBy(cb.asc(root.get(Annonce_.signal)))
			)
			.getResultList();
	}
	
	public void supprimerAnnonce(Annonce a){

		//String query ="Delete from Annonce where Annonce.id =a.id";
		connexion.getEm().remove(a);
		this.listerAnnonces();


	}
	public Annonce signalerAnnonce(Annonce a){
		a.setSignal(a.getSignal()+1);
		connexion.getEm().persist(a);
		connexion.getEm().flush();
		return a;
	}

	public List<Annonce> listerAnnoncesParCategorie(Categorie categorie){

		return categorie.getLesAnnonces();


	}
	public Annonce modifier(Annonce a)
	{
		connexion.getEm().merge(a);
		return a;
	}
	public void supprimerAnnonce(){

		connexion.getEm().getTransaction().begin();
		String query ="Delete from Annonce";
		connexion.getEm().createQuery(query).executeUpdate();
		connexion.getEm().getTransaction().commit();
		System.out.println("Tout est supprimer...");



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
			if ((a.getTitre().indexOf(motCle)>=0) || (a.getDescription().indexOf(motCle)>=0))
			//if ((a.getTitre()==motCle) || (a.getDescription()==motCle))
				annsFinal.add(a);
		}
		System.out.println(annsFinal.size());
		return annsFinal;
		
	}
	

}
