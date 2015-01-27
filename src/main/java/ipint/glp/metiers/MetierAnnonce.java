package ipint.glp.metiers;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.cas.authentication.CasAuthenticationToken;

public class MetierAnnonce {
	
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	public MetierAnnonce(){
		
	}
	
	public Annonce creerAnnonce(Categorie categorie,CasAuthenticationToken principal,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur(principal);
		return FabAnnonce.getInstance().creerAnnonce(categorie, utilisateur, TypeAnnonce.offre, lesChamps);
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
