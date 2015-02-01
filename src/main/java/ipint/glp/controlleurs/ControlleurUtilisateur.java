package ipint.glp.controlleurs;

import ipint.glp.metiers.MetierUtilisateur;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlleurUtilisateur {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurUtilisateur.class);
	
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	@RequestMapping(value = "/utilisateur/connecter", method = RequestMethod.GET)
	public String connecter() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/utilisateur", method = RequestMethod.GET)
	public String profil() {
		return "/utilisateur/profil";
	}
	
	@RequestMapping(value = "/utilisateur/lister/annonce", method = RequestMethod.GET)
	public String listerAnnonce(Model model, HttpServletRequest request) {
		model.addAttribute("annonces", metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal()).getLesAnnonces());
		return "/utilisateur/lister/annonce";
	}
}
