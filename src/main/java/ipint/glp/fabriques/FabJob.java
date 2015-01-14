	package ipint.glp.fabriques;

	import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import connexion.Connexion;


public class FabJob {


	


		static FabJob fb;
		private HashMap<Integer,Job> lesJobs;
		private Connexion connexion;
		
		private FabJob(){
			lesJobs = new HashMap<Integer,Job>();
			connexion = Connexion.getConnexion();

		}
		
		public static FabJob getInstance(){
			if(fb == null){
				fb = new FabJob();
			}
			return fb;
		}
		

		public Job creerJob(String titre, String remuneration, String description,Utilisateur utilisateur){
		//	this.listerAnnonces();
			Job j = new Job();
			j.setTitre(titre);;
			//categorie.addAnnonce(a);
			j.setRemuneration(remuneration);
			j.setUtilisateur(utilisateur);
			j.setDescription(description);
			connexion.getEm().persist(j);
			//utilisateur.addAnnonce(a);
			lesJobs.put(j.getId(),j);
			return j;
		}


		public List<Job> listerJob (){

			Query query = connexion.getEm().createQuery("Select jb from Job jb");
			List<Job> jobs = query.getResultList();
			lesJobs.clear();
			for(Job a : jobs){
				lesJobs.put(a.getId(), a);
			}
			return jobs;
			
		}
		
		public void supprimerJob(Job j){

			String query ="Delete from Job where Job.id =j.id";
			connexion.getEm().createQuery(query).executeUpdate();
			this.listerJob();


		}
		
		public List<Job> chercherJobParMotCle(String mot){
			Query query = connexion.getEm().createQuery("Select jb from Job jb where LOWER(jb.titre) like LOWER(:mot) or LOWER(jb.description) like LOWER(:mot)");
			query.setParameter("mot", "%"+mot+"%");
			//Query query = connexion.getEm().createQuery("Select Job.id from Job where Job.titre ='mot'");
			List<Job> jobs = query.getResultList();
			lesJobs.clear();
			for(Job a : jobs){
				lesJobs.put(a.getId(), a);
			}
			return jobs;
		}
		
		public Job creer(Job o) {
			connexion.getEm().persist(o);
			return o;
		}
		/*public void supprimerAnnonce(Annonce a){

			String query ="Delete from Annonce where Annonce.id =a.id";
			connexion.getEm().createQuery(query).executeUpdate();
			this.listerAnnonces();


		}

		public List<Annonce> listerAnnoncesParCategorie(Categorie categorie){

			return categorie.getLesAnnonces();


		}*/
		public void supprimerJob(){

			connexion.getEm().getTransaction().begin();
			String query ="Delete from Job";
			connexion.getEm().createQuery(query).executeUpdate();
			connexion.getEm().getTransaction().commit();
			System.out.println("Tout est supprimer...");



		}
		
		

	}
