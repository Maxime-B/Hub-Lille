package ipint.glp.controlleurs;

import ipint.glp.metiers.MetierAnnonce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlleurModerateur {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurModerateur.class);
	
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	
	@RequestMapping(value = "/moderateur", method = RequestMethod.GET)
	public String listerParSignalement(Model model) {
		model.addAttribute("annonces", metierAnnonce.listerParSignalement());
		return "/moderateur/annonce/lister";
	}
	
	@RequestMapping(value = "/moderateur/moderer/{id}", method = RequestMethod.GET)
	public String moderer(Model model, @PathVariable Integer id) {
		model.addAttribute("annonce", metierAnnonce.rechercher(id));
		return "/moderateur/annonce/moderer";
	}
}
