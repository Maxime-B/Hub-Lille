package ipint.glp.controlleurs;

import ipint.glp.controlleurs.valideurs.ValideurEvenement;
import ipint.glp.controlleurs.valideurs.ValideurUniciteEvenement;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.metiers.MetierEvenement;
import ipint.glp.metiers.MetierUtilisateur;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurEvenement {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurEvenement.class);
	private MetierEvenement metierEvenement = MetierEvenement.getInstance();
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();

	private ValideurUniciteEvenement valideurUniciteEvenement = new ValideurUniciteEvenement();

	@Inject
	private RoleHierarchyImpl roleHierarchy;

	/**
	 * Register a validator that will be lookup when a parameter is binded to a
	 * handler argument (with @ModelAttribute() for example).
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new ValideurEvenement());
	}

	@RequestMapping(value = "/evenement", method = RequestMethod.GET)
	public ModelAndView lister(Locale locale, Model model) {
		return new ModelAndView("/evenement/lister", "evenements",
				metierEvenement.lister());
	}

	@RequestMapping(value = "/evenement/creer", method = RequestMethod.GET)
	public ModelAndView creer() {
		ModelAndView modelAndView = new ModelAndView("evenement/creer",
				"evenement", new Evenement());
		modelAndView.addObject("page", "creer");
		return modelAndView;
	}

	@RequestMapping(value = "/evenement/creer", method = RequestMethod.POST)
	public ModelAndView creer(HttpServletRequest request,
			@Valid @ModelAttribute("evenement") Evenement evenement,
			BindingResult bindingResultOfEvenement) {
		valideurUniciteEvenement.validate(evenement, bindingResultOfEvenement);
		if (bindingResultOfEvenement.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("evenement/creer",
					"evenement", evenement);
			modelAndView.addObject("page", "creer");
			return modelAndView;
		}
		evenement.setUtilisateur(metierUtilisateur
				.getUtilisateur((CasAuthenticationToken) request
						.getUserPrincipal()));
		metierEvenement.creer(evenement);
		ModelAndView modelAndView = new ModelAndView("evenement/creer");
		modelAndView.addObject("estUnSucces", true);
		modelAndView.addObject("page", "creer");
		modelAndView.addObject("evenement", new Evenement());
		modelAndView.addObject("evenementCree", evenement);
		return modelAndView;
	}

	@RequestMapping(value = "/evenement/modifier/{id}", method = RequestMethod.GET)
	public ModelAndView modifier(@PathVariable Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		// login correspond au créateur ou à un modérateur sinon refus d'acces
		Set<Droit> droitsPrincipal = metierUtilisateur.getUtilisateur(
				(CasAuthenticationToken) request.getUserPrincipal())
				.getDroits();
		Collection<GrantedAuthority> reachableGrantedAuthorities, authorities = new HashSet<GrantedAuthority>();
		for (Droit droitPrincipal : droitsPrincipal) {
			authorities.add(new SimpleGrantedAuthority(droitPrincipal.name()));
		}
		reachableGrantedAuthorities = roleHierarchy
				.getReachableGrantedAuthorities(authorities);
		Utilisateur utilisateur = metierEvenement.obtenir(id).getUtilisateur();
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

		ModelAndView modelAndView = new ModelAndView("evenement/creer",
				"evenement", metierEvenement.obtenir(id));
		modelAndView.addObject("page", "modifier");
		return modelAndView;
	}

	@RequestMapping(value="/evenement/modifier", method = RequestMethod.POST)
	public ModelAndView modifier(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@Valid @ModelAttribute("evenement") Evenement evenement,
			BindingResult bindingResultOfEvenement) {
		// login correspond au créateur ou à un modérateur sinon refus d'acces
		Set<Droit> droitsPrincipal = metierUtilisateur.getUtilisateur(
				(CasAuthenticationToken) request.getUserPrincipal())
				.getDroits();
		Collection<GrantedAuthority> reachableGrantedAuthorities, authorities = new HashSet<GrantedAuthority>();
		for (Droit droitPrincipal : droitsPrincipal) {
			authorities.add(new SimpleGrantedAuthority(droitPrincipal.name()));
		}
		reachableGrantedAuthorities = roleHierarchy
				.getReachableGrantedAuthorities(authorities);
		Utilisateur utilisateur = metierEvenement.obtenir(evenement.getId())
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
		
		if (bindingResultOfEvenement.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("evenement/creer",
					"evenement", evenement);
			modelAndView.addObject("estUnSucces", false);
			modelAndView.addObject("page", "modifier");
			return modelAndView;
		}
		evenement.setUtilisateur(utilisateur);
		metierEvenement.modifier(evenement);
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/utilisateur/lister/evenement");
		modelAndView.addObject("estUnSucces", true);
		modelAndView.addObject("page", "modifier");
		modelAndView.addObject("evenementCree", evenement);
		return modelAndView;
	}
	
	@RequestMapping(value="/evenement/supprimer/{id}", method = RequestMethod.GET)
	public ModelAndView supprimerAnnonce(Model model, @PathVariable Integer id) {
		metierEvenement.supprimer(metierEvenement.obtenir(id));
		return new ModelAndView("utilisateur/lister/evenement");
	}
}
