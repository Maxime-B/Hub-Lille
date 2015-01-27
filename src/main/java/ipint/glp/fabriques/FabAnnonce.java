package ipint.glp.fabriques;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

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
	public void supprimerAnnonce(Annonce a){

		String query ="Delete from Annonce where Annonce.id =a.id";
		connexion.getEm().createQuery(query).executeUpdate();
		this.listerAnnonces();


	}

	public List<Annonce> listerAnnoncesParCategorie(Categorie categorie){

		return categorie.getLesAnnonces();


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
	
	

}
