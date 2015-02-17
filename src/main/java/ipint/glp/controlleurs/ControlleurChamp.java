package ipint.glp.controlleurs;

import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.metiers.MetierChamp;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControlleurChamp {

	MetierChamp metierChamp = new MetierChamp();
	
	@RequestMapping(value = "/admin/nouveauChamp", method = RequestMethod.GET)
	public String nouveauChamp(Locale locale, Model model) {
		model.addAttribute("champ", new Champ());
		model.addAttribute("typeChamps",TypeChamp.values());
		return "/admin/nouveauChamp";
	}
	
	@RequestMapping(value = "/admin/creationChamp", method = RequestMethod.POST)
	public String creationChamp(@ModelAttribute("champ")Champ champ,Locale locale, Model model) {
		metierChamp.creerChamp(champ.getLibelle(), champ.getTypeChamp(), champ.isObligatoire());
		model.addAttribute("champs",metierChamp.listerChamps());
		return "/admin/nouvelleCategorie";
	}
}
