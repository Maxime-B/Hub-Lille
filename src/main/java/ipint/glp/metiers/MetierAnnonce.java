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
	private FabAnnonce fabAnnonce = FabAnnonce.getInstance();
	
	public MetierAnnonce(){
		
	}
	
	/*public Annonce creerAnnonce(Categorie categorie,String titre,String description,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		Annonce annonce = FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, TypeAnnonce.offre, lesChamps);
		return annonce;
	}*/
	public Annonce creerAnnonce(Categorie categorie,String titre, String description,Utilisateur utilisateur,TypeAnnonce typeAnnonce, HashMap<String, String> lesChamps){
		System.out.println("----"+typeAnnonce);
		return FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, typeAnnonce, lesChamps);

		/*if(typeAnnonce == TypeAnnonce.offre)
			return FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, TypeAnnonce.offre, lesChamps);
		else 
			return FabAnnonce.getInstance().creerAnnonce(categorie,titre,description, utilisateur, TypeAnnonce.demande, lesChamps);
		*/}
	

	public boolean signalerAnnonce(Utilisateur u,  Annonce a){
		
		if(u.getLesAnnoncesSignales().contains(a)){
			
			return false;
		}
		else{
			u.getLesAnnoncesSignales().add(a);
		FabAnnonce.getInstance().signalerAnnonce(a);
		return true;
		}
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
	public List<Annonce> listerAnnonces(TypeAnnonce typeAnnonce){
		return FabAnnonce.getInstance().listerAnnonces(typeAnnonce);
	}
	
	public List<Annonce> listerAnnoncesParType(TypeAnnonce type){
		return FabAnnonce.getInstance().chercherAnnoncesParType(type);
	}
	
	public List<Annonce> chercherAnnonceParTypeCate(String categorie, TypeAnnonce type){
		return FabAnnonce.getInstance().chercherAnnoncesParTypeCate(type, FabCategorie.getInstance().getCategorie(categorie));
	}
	
	public List<Annonce> chercherAnnonceParTypeMotCle(TypeAnnonce type,String motCle){
		return FabAnnonce.getInstance().chercherAnnoncesParTypeMotCle(type, motCle);
	}
	
	public List<Annonce> chercherAnnoncesParTypeMotCleCate(TypeAnnonce type, String categorie, String motCle){
	return FabAnnonce.getInstance().chercherAnnoncesParTypeMotCleCate(type, FabCategorie.getInstance().getCategorie(categorie), motCle);
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

	public List<Annonce> getAnnoncesPerimees() {
		return fabAnnonce.getAnnoncesPerimees();
	}

	public static void main(String[] args){
		System.out.println(new MetierAnnonce().getAnnoncesPerimees());
	}
}
