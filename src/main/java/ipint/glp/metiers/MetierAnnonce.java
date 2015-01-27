package ipint.glp.metiers;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabJob;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

public class MetierAnnonce {
	
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	public MetierAnnonce(){
		
	}
	
	public Annonce creerAnnonce(Categorie categorie,Principal principal,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
//		metierUtilisateur
//		Annonce annonce = FabAnnonce.getInstance().creerAnnonce(categorie, principal, TypeAnnonce.offre, lesChamps);
//		return annonce;
		return null;
	}
	
	public List<Annonce> listerAnnoncesParCategorie(String categorie){
		return FabAnnonce.getInstance().listerAnnoncesParCategorie(FabCategorie.getInstance().getCategorie(categorie));
	}
	
	public List<Annonce> listerAnnonces(){
		return FabAnnonce.getInstance().listerAnnonces();
	}
	
	

	public void supprimerAnnonce(Annonce a) {
		FabAnnonce.getInstance().supprimerAnnonce(a);
	}

	public void supprimerAnnonces() {
		FabAnnonce.getInstance().supprimerAnnonce();
	}

	public Annonce rechercher(int reference) {
		// TODO Auto-generated method stub
		return FabAnnonce.getInstance().rechercherParId(reference);
	}
	
	

}
