package ipint.glp.fabriques;

import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
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
	

	public Annonce creerAnnonce(Categorie categorie,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		this.listerAnnonces();
		Annonce a = new Annonce();
		a.setCategorie(categorie);
		a.setType(typeAnnonce);
		a.setUtilisateur(utilisateur);
		a.setLesChamps(lesChamps);
		connexion.getEm().persist(a);
		lesAnnonces.put(a.getId(),a);
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
	public void supprimerAnnonce(Annonce a){

		String query ="Delete from Annonce where Annonce.id =a.id";
		connexion.getEm().createQuery(query).executeUpdate();
		System.out.println("L'annonce a ete supprime ...");


	}

	public void chercherAnnonce(String categorie){

		connexion.getEm().getTransaction().begin();

		Query query = connexion.getEm().createQuery("select a from Annonce a where a.Categorie = categorie");
		List<Annonce> annonces = query.getResultList();
		for (Annonce a : annonces) {
			System.out.println("annonce par categorie: " +a.getId());
		}



	}
	public void supprimerAnnonce(){

		connexion.getEm().getTransaction().begin();
		String query ="Delete from Annonce";
		connexion.getEm().createQuery(query).executeUpdate();
		
	

		connexion.getEm().getTransaction().commit();
		System.out.println("Tout est supprimer...");



	}
	
	

}
