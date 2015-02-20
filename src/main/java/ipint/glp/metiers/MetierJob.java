package ipint.glp.metiers;


import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabJob;
import ipint.glp.fabriques.FabUtilisateur;

import java.util.HashMap;
import java.util.List;
public class MetierJob {





	
	public MetierJob(){
		
	}
	
	
	
	public Job creerJob(String titre, String remuneration, String description,String modalite,Utilisateur utilisateur){
		Job job = FabJob.getInstance().creerJob(titre,description , remuneration,modalite, utilisateur );
		System.out.println("publication réussie ");
		for (Job an : FabJob.getInstance().listerJob()) {
			System.out.println(an.getId() + " " + an.getDescription()
					+ " " + an.getUtilisateur().getNom() + " " + an.getRemuneration());
		}
		return job;
	}
	
	
	public List<Job> listerJobs(){
		return FabJob.getInstance().listerJob();
	}

	public void supprimer(Job a) {
		FabJob.getInstance().supprimer(a);
	}

	public void supprimerJob() {
		FabJob.getInstance().supprimerJob();
	}
	public Job rechercher(int reference) {
		// TODO Auto-generated method stub
		return FabJob.getInstance().rechercherParId(reference);
	}
	
	public Job creerJob(Job j){
		return FabJob.getInstance().creer(j);
	}
	public List<Job> chercherJob(String mot){
		return FabJob.getInstance().chercherJobParMotCle(mot);
		
	}

}
