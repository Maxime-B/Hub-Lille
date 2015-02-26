package ipint.glp.controlleurs;


import ipint.glp.controlleurs.valideurs.ValidateurContact;
import ipint.glp.controlleurs.valideurs.ValideurAnnonce;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Job;
import ipint.glp.donnees.Publication;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierEvenement;
import ipint.glp.metiers.MetierJob;
import ipint.glp.metiers.MetierPublication;
import ipint.glp.metiers.MetierUtilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

@Controller
public class ControlleurPublication implements ServletContextAware{

	private static final Logger logger = LoggerFactory.getLogger(ControlleurAnnonce.class);
	private MetierPublication metierPublication = new MetierPublication();
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierEvenement metierEvenement = MetierEvenement.getInstance();
	private MetierJob metierJob = new MetierJob();
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	ValideurAnnonce valideurAnnonce =  new ValideurAnnonce();
	ValidateurContact validateurcontact = new ValidateurContact();
	@Autowired
	ServletContext servletcontext;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/publication", method = RequestMethod.GET)
	public String listerPublications(Locale locale,Model model){
		List<Publication>lesPublications = new ArrayList<Publication>();
		model.addAttribute("publications", metierPublication.listerPublications());	
		return "publication/lister";
	}	
	
	@RequestMapping(value = "/publication/motcle", method = RequestMethod.GET)
	public String listerPubMotCle(Locale locale,Model model, @RequestParam("motCle")String motCle, @RequestParam String publication){
		List<? extends Publication>lesPublications = new ArrayList<Publication>();
		System.out.println("listerPubMotCle");
		System.out.println(motCle);
		System.out.println(publication);
		String type = "";
		
		if ( ((motCle == null || (motCle.equals("")) && (publication == null || publication.equals(""))))) {
			System.out.println("toutes les publications");
			type = "mix";
			lesPublications= metierPublication.listerPublications();
		}
		
		if ( ((motCle == null || (motCle.equals("")) && (publication != null && (!publication.equals("")))))) {
			if(publication.equals("annonces-offres")){
				model.addAttribute("type", "Annonce");
				System.out.println("toutes les publications d'offre");
				lesPublications = metierAnnonce.listerAnnoncesParType(TypeAnnonce.offre);
			}
			if (publication.equals("annonces-demandes")) {
				model.addAttribute("type", "Annonce");
				System.out.println("toutes les publications de demande");
				lesPublications = metierAnnonce.listerAnnoncesParType(TypeAnnonce.demande);
			}
			if (publication.equals("jobs")) {
				model.addAttribute("type", "Job");
				System.out.println("toutes les jobs");
				lesPublications = metierJob.listerJobs();
			} 
			if(publication.equals("evenements")){
				model.addAttribute("type", "Evenement");
				System.out.println("toutes les événements");
				lesPublications = metierEvenement.lister();
			}
		}
		
		
		if ( ((motCle != null && (!(motCle.equals(""))) && (publication == null || publication.equals(""))))) {
			System.out.println("toutes les publications par mot cle");
			type = "mix";
			lesPublications = metierPublication.listerPubMotCle(motCle);
		}
		if ( ((motCle != null && (!(motCle.equals(""))) && (publication != null && (!publication.equals("")))))) {
			if(publication.equals("annonces-offres")){
				model.addAttribute("type", "Annonce");
				System.out.println("toutes les publications d'offre par mot cle");
				lesPublications = metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.offre, motCle);
			}
			if (publication.equals("annonces-demandes")) {
				model.addAttribute("type", "Annonce");
				System.out.println("toutes les publications de demande par mot cle");
				lesPublications = metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.demande, motCle);
			}
			if (publication.equals("jobs")) {
				model.addAttribute("type", "Job");
				System.out.println("toutes les jobs par mot cle");
				lesPublications = metierJob.chercherJob(motCle);
			}
			if(publication.equals("evenements")){
				model.addAttribute("type", "Evenement");
				System.out.println("toutes les évenement par mot cle");
				lesPublications = metierEvenement.chercherParMotCle(motCle);
			}
		}
		if ("mix".equals(type)) {
			System.out.println("mix");
			List<String> lesTypes = new ArrayList<String>();
			for (Publication pub: lesPublications) {
				if (pub instanceof Annonce){
					lesTypes.add("Annonce");
				} else if (pub instanceof Evenement){
					lesTypes.add("Evenement");
				} else if (pub instanceof Job){
					lesTypes.add("Job");
				}
			}
			model.addAttribute("type", "mix");
			model.addAttribute("publications", lesPublications);
			model.addAttribute("types", lesTypes);
		} else {
			model.addAttribute("publications", lesPublications);
		}
		System.out.println(lesPublications.size());
		return "/publication/listerPublication";
	}	

	@Override
	public void setServletContext(ServletContext sc) {
		this.servletcontext = sc;
		
	}
	
}