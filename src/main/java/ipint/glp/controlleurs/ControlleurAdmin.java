package ipint.glp.controlleurs;

import ipint.glp.controlleurs.forms.FormDroit;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.metiers.MetierUtilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	@RequestMapping(value = "/admin/droit", method = RequestMethod.GET)
	public ModelAndView gererDroitFrom(Model model) {
		model.addAttribute("utilisateurs", metierUtilisateur.listerParRole());
		model.addAttribute("droits", Droit.values());
		return new ModelAndView("/admin/droit/modifier","utilisateur", new FormDroit());
	}

	@RequestMapping(value = "/admin/droit", method = RequestMethod.POST)
	public String gererDroit(Model model, @ModelAttribute("utilisateur") FormDroit formDroit) {
		Utilisateur utilisateur = metierUtilisateur.getUtilisateur(formDroit.getLogin());
		//TODO validateur
//		Droit droitObject;
//		try {
//			droitObject = Droit.valueOf(droit);
//		} catch (IllegalArgumentException illegalArgumentException) {
//			return "/admin/droit/modifier";
//		}
		
		utilisateur.setDroits(formDroit.getDroitsObject());
		metierUtilisateur.modifier(utilisateur);
		model.addAttribute("utilisateurs", metierUtilisateur.listerParRole());
		model.addAttribute("droits", Droit.values());
		model.addAttribute("estUnSucces", true);
		return "/admin/droit/modifier";
	}
}
