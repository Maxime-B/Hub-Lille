package ipint.glp.controlleurs;


import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;

import ipint.glp.donnees.Job;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.fabriques.FabUtilisateur;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;
import ipint.glp.metiers.MetierChamp;
import ipint.glp.metiers.MetierJob;
import ipint.glp.metiers.MetierUtilisateur;

import javax.*;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControlleurJob {

	
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurJob.class);

	private MetierJob metierJob = new MetierJob();
	
	
	
	
	
	
	@RequestMapping(value = "/job/creer", method = RequestMethod.GET)
	   public String VueJob(Model model) {
		Job job = new Job();
		model.addAttribute("job", job) ;
		
		//mv = new ModelAndView("job", "command", jb);
	      return "job/creer";
	   }
	

	   
	   @RequestMapping(value = "/job/creer", method = RequestMethod.POST)
	   public String addJob(@ModelAttribute("job")Job job, 
	   Model model) {
	
	      model.addAttribute("titre", job.getTitre());
	      model.addAttribute("description", job.getDescription());
	      model.addAttribute("remuneration", job.getRemuneration());
	      System.out.println(job.getDescription());
	  //   metierJob.creerJob(job.getTitre(),job.getDescription(), job.getRemuneration(), new Utilisateur());
	      metierJob.creerJob(job);
	      return "job/jobs";
	
	
	   }
	   
	   
	 
	   
	@RequestMapping(value = "/job/listerJob", method = RequestMethod.GET)
	public String jobs(Locale locale, Model model) {
		model.addAttribute("jobs", metierJob.listerJobs());
		return "job/listerJob";
	}
	
}