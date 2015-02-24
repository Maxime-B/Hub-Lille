package ipint.glp.controlleurs;


import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.controlleurs.forms.FormContact;
import ipint.glp.controlleurs.valideurs.ValidateurContact;
import ipint.glp.controlleurs.valideurs.ValidateurJob;
import ipint.glp.controlleurs.valideurs.ValideurEvenement;
import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.EmailManager;
import ipint.glp.donnees.Evenement;
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
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ControlleurJob implements ServletContextAware {

	
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurJob.class);

	private MetierJob metierJob = new MetierJob();
	ValidateurJob validateurjob = new ValidateurJob();
	ValidateurContact validateurcontact = new ValidateurContact();
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	@Autowired
	ServletContext servletcontext;
	@Inject
	private RoleHierarchyImpl roleHierarchy;
	
	
	
	
	@RequestMapping(value = "/job/creer", method = RequestMethod.GET)
	   public String VueJob(Model model) {
		Job job = new Job();
		model.addAttribute("job", job) ;
		
		//mv = new ModelAndView("job", "command", jb);
	      return "job/creer";
	   }
	


	   
	   @RequestMapping(value = "/job/creer", method = RequestMethod.POST)
	   public String addJob( Model model, HttpServletRequest request,@ModelAttribute("job")Job job,BindingResult bindingResultOfJob) {
		  
		   validateurjob.validate(job,bindingResultOfJob );
		   if (bindingResultOfJob.hasErrors()) {
			   System.out.println("erreur");
			   model.addAttribute("estUnSucces", false);
				return "job/creer";
			}
	
		   else{
			   model.addAttribute("estUnSucces", true);
	      model.addAttribute("titre", job.getTitre());
	      model.addAttribute("description", job.getDescription());
	      model.addAttribute("remuneration", job.getRemuneration());
	      model.addAttribute("modalite", job.getModalite());
	      System.out.println(job.getDescription());
	  //   metierJob.creerJob(job.getTitre(),job.getDescription(), job.getRemuneration(), new Utilisateur());
	     job.setUtilisateur(metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal()));
	      metierJob.creerJob(job);
	      return "redirect:/job/consulter?ref=" + job.getId();
		   }
	
	
	   }
	   
	   

	@RequestMapping(value = "/job/listerJob", method = RequestMethod.GET)
	public String jobs(Locale locale, Model model,String mot) {
		List<Job>lesJobs = new ArrayList<Job>();
		if( mot == null)
			lesJobs = metierJob.listerJobs();
		
		else lesJobs =metierJob.chercherJob(mot);
		
		model.addAttribute("jobs",lesJobs );
		model.addAttribute("mot",mot);
		return "job/listerJob";
	}
	
/*	@RequestMapping(value = "/job/chercherJob", method = RequestMethod.GET)
	public String chercher(Locale locale, Model model, String mot) {
		model.addAttribute("lesjobsCherches", metierJob.chercherJob(mot));
		model.addAttribute("mot",mot);
		return "job/chercherJob";
	}*/
	@RequestMapping(value = "/job/consulter", method = RequestMethod.GET)
	public String consulterAnnonce(Model model, @RequestParam("ref") int ref) {
		Job job = metierJob.rechercher(ref);
		model.addAttribute("job", job);
		model.addAttribute("ref",ref);
		return "job/consulter";
	}

	@Override
	public void setServletContext(ServletContext sc) {
		this.servletcontext = sc;
		
	}
	
	@RequestMapping(value = "/job/contacter")
	public String contactAnnonce(@RequestParam("ref")int ref, @ModelAttribute("formcontact") FormContact formcontact,HttpServletRequest request,
			BindingResult bindingResultOfContact,Model model)
			{
				Job job = metierJob.rechercher(ref);
				Utilisateur utilisateur = metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal());
				formcontact.setEmeteur(utilisateur.getEmail());
				

				model.addAttribute("a", job);
				return "job/contacter";
			}
		
		@RequestMapping(value = "/job/contacter",method = RequestMethod.POST)
		public String contacterAnnonce(@RequestParam("ref")int ref, @ModelAttribute("formcontact") FormContact formcontact,
				BindingResult bindingResultOfContact,Model model)
				{
			validateurcontact.validate(formcontact, bindingResultOfContact);
			if (bindingResultOfContact.hasErrors()) {
				return "job/contacter";
			}
			
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
	    	EmailManager mm = (EmailManager) context.getBean("mailMail");
	    	Job job = metierJob.rechercher(ref);
			formcontact.setDestinataire(job.getUtilisateur().getEmail());

	    	
	    	mm.sendMail(formcontact.getEmeteur(), formcontact.getDestinataire(), formcontact.getObjet(), formcontact.getMessage());
			model.addAttribute("estUnSucces", true);
			model.addAttribute("a", job);
					return "job/contacter";
				}
		
		@RequestMapping(value="/job/supprimer/{id}", method = RequestMethod.GET)
		public ModelAndView supprimerAnnonce(Model model, @PathVariable Integer id) {
			System.out.println(id);
			metierJob.supprimer(metierJob.rechercher(id));
			return new ModelAndView("redirect:/utilisateur/lister/job");
		}

		
		@RequestMapping(value = "/job/modifier/{id}", method = RequestMethod.GET)
		public ModelAndView modifier(@PathVariable Integer id,
				HttpServletRequest request, HttpServletResponse response) {
			// login correspond au crÃ©ateur ou Ã  un modÃ©rateur sinon refus d'acces
			Set<Droit> droitsPrincipal = metierUtilisateur.getUtilisateur(
					(CasAuthenticationToken) request.getUserPrincipal())
					.getDroits();
			Collection<GrantedAuthority> reachableGrantedAuthorities, authorities = new HashSet<GrantedAuthority>();
			for (Droit droitPrincipal : droitsPrincipal) {
				authorities.add(new SimpleGrantedAuthority(droitPrincipal.name()));
			}
			reachableGrantedAuthorities = roleHierarchy
					.getReachableGrantedAuthorities(authorities);
			Utilisateur utilisateur = metierJob.rechercher(id).getUtilisateur();
			if (!reachableGrantedAuthorities.contains(new SimpleGrantedAuthority(
					Droit.MODERATEUR.name()))) {
				if (!utilisateur.getLogin().equals(request.getRemoteUser())) {
					try {
						response.sendError(HttpServletResponse.SC_FORBIDDEN);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return new ModelAndView("index");
				}
			}
			//fin authentification

			ModelAndView modelAndView = new ModelAndView("/job/modifier",
					"job", metierJob.rechercher(id));
			//modelAndView.addObject("page", "modifier");
			return modelAndView;
		}

		@RequestMapping(value="/job/modifier", method = RequestMethod.POST)
		public ModelAndView modifier(HttpServletRequest request,
				HttpServletResponse response, Model model,
				@Valid @ModelAttribute("Job") Job job,
				BindingResult bindingResultOfJob) {
			// login correspond au crÃ©ateur ou Ã  un modÃ©rateur sinon refus d'acces
			Set<Droit> droitsPrincipal = metierUtilisateur.getUtilisateur(
					(CasAuthenticationToken) request.getUserPrincipal())
					.getDroits();
			Collection<GrantedAuthority> reachableGrantedAuthorities, authorities = new HashSet<GrantedAuthority>();
			for (Droit droitPrincipal : droitsPrincipal) {
				authorities.add(new SimpleGrantedAuthority(droitPrincipal.name()));
			}
			reachableGrantedAuthorities = roleHierarchy
					.getReachableGrantedAuthorities(authorities);
			Utilisateur utilisateur = metierJob.rechercher(job.getId())
					.getUtilisateur();
			if (!reachableGrantedAuthorities.contains(new SimpleGrantedAuthority(
					Droit.MODERATEUR.name()))) {
				System.out.println("pas admin");
				if (!utilisateur.getLogin().equals(request.getRemoteUser())) {
					System.out.println("pas proprio");
					try {
						response.sendError(HttpServletResponse.SC_FORBIDDEN);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return new ModelAndView("index");
				}
			}
			//fin authentification
			
			if (bindingResultOfJob.hasErrors()) {
				ModelAndView modelAndView = new ModelAndView("/job/modifier",
						"job", job);
				modelAndView.addObject("estUnSucces", false);
				//modelAndView.addObject("page", "modifier");
				return modelAndView;
			}
			job.setUtilisateur(utilisateur);
			metierJob.modifier(job);
			ModelAndView modelAndView = new ModelAndView(
					"redirect:/utilisateur/lister/job");
			modelAndView.addObject("estUnSucces", true);
			//modelAndView.addObject("page", "modifier");
			modelAndView.addObject("jobCree", job);
			return modelAndView;
		}
		
		
		
		
		
	
}