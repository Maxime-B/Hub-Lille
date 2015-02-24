package ipint.glp.controlleurs;

import ipint.glp.controlleurs.forms.FormDroit;
import ipint.glp.donnees.Droit;
import ipint.glp.metiers.MetierUtilisateur;
import ipint.glp.metiers.exceptions.AuMoinsUnAdminException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurAdmin {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAdmin.class);
	
	@Inject
	private RoleHierarchyImpl roleHierarchy;
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	@RequestMapping(value = "/admin/droit", method = RequestMethod.GET)
	public ModelAndView gererDroitFrom(Model model) {
		model.addAttribute("utilisateurs", metierUtilisateur.listerParRole());
		model.addAttribute("droits", Droit.values());
		model.addAttribute("definitionsDesDroits", getAllReachableGrantedAuthorities());
		return new ModelAndView("/admin/droit/modifier","utilisateur", new FormDroit());
	}

	//utilisé uniquement si le javascript est désactivé
	@RequestMapping(value = "/admin/droit", method = RequestMethod.POST)
	public String gererDroit(Model model, @ModelAttribute("utilisateur") FormDroit formDroit) {
		model.addAttribute("estUnSucces", true);
		try {
			metierUtilisateur.modifierRole(formDroit.getLogin(), formDroit.getDroitsObject());
		} catch (AuMoinsUnAdminException e) {
			model.addAttribute("AuMoinsUnAdminException", true);
			model.addAttribute("estUnSucces", false);
			logger.debug("Le dernier Admin " + formDroit.getLogin() + " ne peut être supprimé", formDroit);
		}
		model.addAttribute("utilisateurs", metierUtilisateur.listerParRole());
		model.addAttribute("droits", Droit.values());
		model.addAttribute("definitionsDesDroits", getAllReachableGrantedAuthorities());
		return "/admin/droit/modifier";
	}
	
	public HashMap<Droit, Collection<GrantedAuthority>> getAllReachableGrantedAuthorities(){
		HashMap<Droit, Collection<GrantedAuthority>> allReachableGrantedAuthorities = new HashMap<Droit, Collection<GrantedAuthority>>();
		for (Droit droit: Droit.values()){
			Collection<GrantedAuthority> reachableGrantedAuthorities, authorities = new HashSet<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(droit.name()));
			reachableGrantedAuthorities = roleHierarchy
					.getReachableGrantedAuthorities(authorities);
			allReachableGrantedAuthorities.put(droit, reachableGrantedAuthorities);
		}
		return allReachableGrantedAuthorities;
	}
}
