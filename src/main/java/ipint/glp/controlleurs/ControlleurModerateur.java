package ipint.glp.controlleurs;

import javax.servlet.http.HttpServletRequest;

import ipint.glp.donnees.Annonce;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierUtilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlleurModerateur {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurModerateur.class);
	
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
	
	@RequestMapping(value = "/moderateur", method = RequestMethod.GET)
	public String listerParSignalement(Model model) {
		model.addAttribute("annonces", metierAnnonce.listerParSignalement());
		return "/moderateur/annonce/lister";
	}
	
	@RequestMapping(value = "/moderateur", method = RequestMethod.POST)
	public String listerParSignalement2(Model model,HttpServletRequest request,@RequestParam("ref") int reference,@RequestParam("typeAction") String action) {
		Annonce an = metierAnnonce.rechercher(reference);

		if(action.equalsIgnoreCase("supprimer"))
		{
			metierUtilisateur.getUtilisateur((CasAuthenticationToken) request.getUserPrincipal()).getLesAnnonces().remove(an);
			metierAnnonce.supprimerAnnonce(an);
		}
		model.addAttribute("annonces", metierAnnonce.listerParSignalement());
		return "/moderateur/annonce/lister";
	}
	
	@RequestMapping(value = "/moderateur/moderer/{id}", method = RequestMethod.GET)
	public String moderer(Model model, @PathVariable Integer id) {
		model.addAttribute("annonce", metierAnnonce.rechercher(id));
		return "/moderateur/annonce/moderer";
	}
}
