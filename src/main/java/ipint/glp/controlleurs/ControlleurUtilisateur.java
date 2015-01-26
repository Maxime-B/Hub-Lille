package ipint.glp.controlleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlleurUtilisateur {
	@RequestMapping(value = "/utilisateur/connecter", method = RequestMethod.GET)
	public String connecter() {
		return "redirect:/";
	}
	
	@RequestMapping(value = "/utilisateur", method = RequestMethod.GET)
	public String profil() {
		return "/utilisateur/profil";
	}
}
