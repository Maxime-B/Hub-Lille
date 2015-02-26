package ipint.glp.metiers;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.Publication;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.fabriques.FabPublication;

import java.util.Arrays;
import java.util.List;

public class MetierPublication {


	public List<Publication> listerPublications(){
		return FabPublication.getInstance().listerPublications();
	}
	public List<Annonce> listerPubAnnonces (){
		return FabPublication.getInstance().listerPubAnnonces();
	}
	public List<Job> listerPubJobs (){
		return FabPublication.getInstance().listerPubJobs();
	}
	public List<Evenement> listerPubEvenements() {
		return FabPublication.getInstance().listerPubEvenements();
	}
	public List<Publication> listerPubMotCle(String motCle){
		return FabPublication.getInstance().listerPubMotCle(motCle);
	}
	public static void main (String[] args) {
		new MetierJob().creerJob("titre", "remuneration", "description", "modalite", new MetierUtilisateur().getUtilisateur("utilisateur"));
		System.out.println(Arrays.toString(new MetierPublication().listerPubMotCle("titre").toArray()));
	}
	public List<Publication> listerPubAnnonces(String publication,
			TypeAnnonce offre) {
		return null;
	}
	
/*	public void chercherPublicationParAnnonceOffre(String motCle, Annonce offre){
		;
	}

	public void chercherPublicationParAnnonceDemande(String motCle, TypeAnnonce typeAnnonce){
		;
	}

	public void chercherPublicationParJob(String motCle, job){
		;
	}

	public void chercherPublicationParEvenement(String motCle, evenement){
		;
	}*/
}

