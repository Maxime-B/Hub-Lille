package ipint.glp.metiers;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabJob;
import ipint.glp.metiers.*;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.cas.authentication.CasAuthenticationToken;

public class MetierAnnonce {

	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	public MetierAnnonce(){
		
	}
	
	/*public Annonce creerAnnonce(Categorie categorie,String titre,String description,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		Annonce annonce = FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, TypeAnnonce.offre, lesChamps);
		return annonce;
	}*/
	public Annonce creerAnnonce(Categorie categorie,String titre, String description,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		return FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, TypeAnnonce.offre, lesChamps);
	}
	
	public Annonce signalerAnnonce(Annonce a){
		return FabAnnonce.getInstance().signalerAnnonce(a);
	}
	public List<Annonce> listerAnnoncesParCategorie(String categorie){
		return FabAnnonce.getInstance().listerAnnoncesParCategorie(FabCategorie.getInstance().getCategorie(categorie));
	}
	public List<Annonce> chercherAnnonceParMotCle(String motCle){
		return FabAnnonce.getInstance().chercherAnnonceParMotCle(motCle);
	}
	public List<Annonce> chercherAnnonceParMotCleCate(String motCle,String categorie){
		return FabAnnonce.getInstance().chercherAnnonceParMotCleCate(motCle,FabCategorie.getInstance().getCategorie(categorie));
	}
	public List<Annonce> listerAnnonces(){
		return FabAnnonce.getInstance().listerAnnonces();
	}
	
	
	public Annonce modifier(Annonce a)
	{
		return FabAnnonce.getInstance().modifier(a);
	}
	public void supprimerAnnonce(Annonce a) {
		FabAnnonce.getInstance().supprimerAnnonce(a);
	}

	public void supprimerAnnonces() {
		FabAnnonce.getInstance().supprimerAnnonce();
	}

	public Annonce rechercher(int reference) {
		return FabAnnonce.getInstance().rechercherParId(reference);
	}
	
	public List<Annonce> listerParSignalement() {
		return FabAnnonce.getInstance().listerAnnoncesParSignalement();
	}
	
	public MetierUtilisateur getMetierUtilisateur() {
		return metierUtilisateur;
	}

	public void setMetierUtilisateur(MetierUtilisateur metierUtilisateur) {
		this.metierUtilisateur = metierUtilisateur;
	}

}
