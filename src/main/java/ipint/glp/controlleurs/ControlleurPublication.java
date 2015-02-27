package ipint.glp.controlleurs;


import ipint.glp.controlleurs.forms.FormRecherche;
import ipint.glp.donnees.Publication;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierEvenement;
import ipint.glp.metiers.MetierJob;
import ipint.glp.metiers.MetierPublication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlleurPublication {

	private static final Logger logger = LoggerFactory.getLogger(ControlleurPublication.class);
	private MetierPublication metierPublication = new MetierPublication();
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierJob metierJob = new MetierJob();
	private MetierEvenement metierEvenement = MetierEvenement.getInstance();
	
	@RequestMapping(value = "/publication")
	public String listerPubMotCle(Model model, @RequestParam(defaultValue="") String motCle, @RequestParam(defaultValue="") String where) {
		List<? extends Publication> lesPublications = null;
		final boolean publicationsMixtes = where.equals("");
		
		if (motCle.equals("")) {
			if (where.equals("")) {
				lesPublications = metierPublication.listerPublications();
			} else {
				if(where.equals("annonces-offres")) {
					model.addAttribute("type", "Annonce");
					lesPublications = metierAnnonce.listerAnnoncesParType(TypeAnnonce.offre);
				} else if (where.equals("annonces-demandes")) {
					model.addAttribute("type", "Annonce");
					lesPublications = metierAnnonce.listerAnnoncesParType(TypeAnnonce.demande);
				} else if (where.equals("jobs")) {
					model.addAttribute("type", "Job");
					lesPublications = metierJob.listerJobs();
				} else if(where.equals("evenements")) {
					model.addAttribute("type", "Evenement");
					lesPublications = metierEvenement.lister();
				}
			}
		} else {
			if (where.equals("")) {
				lesPublications = metierPublication.listerPubMotCle(motCle);
			} else {
				if (where.equals("annonces-offres")) {
					model.addAttribute("type", "Annonce");
					lesPublications = metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.offre, motCle);
				} else if (where.equals("annonces-demandes")) {
					model.addAttribute("type", "Annonce");
					lesPublications = metierAnnonce.chercherAnnonceParTypeMotCle(TypeAnnonce.demande, motCle);
				} else if (where.equals("jobs")) {
					model.addAttribute("type", "Job");
					lesPublications = metierJob.chercherJob(motCle);
				} else if (where.equals("evenements")) {
					model.addAttribute("type", "Evenement");
					lesPublications = metierEvenement.chercherParMotCle(motCle);
				}
			}
		}
		
		model.addAttribute("publicationsMixtes", publicationsMixtes);
		if (publicationsMixtes) {
			List<String> lesTypes = new ArrayList<String>();
			for (Publication pub: lesPublications) {
				lesTypes.add(pub.getClass().getSimpleName());
			}
			model.addAttribute("publications", lesPublications);
			model.addAttribute("types", lesTypes);
		} else {
			model.addAttribute("publications", lesPublications);
		}
		FormRecherche formRecherche = new FormRecherche();
		formRecherche.setMotCle(motCle);
		formRecherche.setWhere(where);
		model.addAttribute("search", formRecherche);
		return "/publication/listerPublication";
	}
}