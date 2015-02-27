	package ipint.glp.fabriques;

	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Evenement;
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
		

		public Job creerJob(String titre, String remuneration, String description,String modalite,Utilisateur utilisateur){
		//	this.listerAnnonces();
			Job j = new Job();
			j.setTitre(titre);
			//categorie.addAnnonce(a);
			j.setRemuneration(remuneration);
			j.setUtilisateur(utilisateur);
			j.setDescription(description);
			j.setModalite(modalite);
			connexion.getEm().persist(j);
			utilisateur.getLesJobs().add(j);
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
		
		public Job rechercherParId(int reference) {
			// TODO Auto-generated method stub
			String query ="select j from Job j where j.id ="+reference;
			ArrayList<Job> l = (ArrayList<Job>) connexion.getEm().createQuery(query).getResultList();
			if (l.isEmpty()) {
				return null;
			}
			return l.get(0);
		}
		public void supprimerJob(Job j){

			String query ="Delete from Job where Job.id =j.id";
			connexion.getEm().createQuery(query).executeUpdate();
			this.listerJob();


		}
		public void supprimer(Job o) {
			connexion.getEm().remove(o);
			o.getUtilisateur().getLesJobs().remove(o);
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
			connexion.getEm().flush();
			o.getUtilisateur().getLesJobs().add(o);
			return o;
		}
		public Job modifier(Job j) {
			connexion.getEm().merge(j);
			return j;
		}
		
		public void supprimerJob(){

			connexion.getEm().getTransaction().begin();
			String query ="Delete from Job";
			connexion.getEm().createQuery(query).executeUpdate();
			System.out.println("Tout est supprimer...");



		}
		
		

	}
