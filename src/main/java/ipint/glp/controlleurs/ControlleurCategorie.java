package ipint.glp.controlleurs;

import ipint.glp.donnees.Champ;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabEvenement;
import ipint.glp.fabriques.FabJob;
import ipint.glp.metiers.MetierCategorie;
import ipint.glp.metiers.MetierChamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlleurCategorie {

	MetierCategorie metierCategorie = new MetierCategorie();
	MetierChamp metierChamp = new MetierChamp();

	@RequestMapping(value = "/admin/categorieAdmin", method = RequestMethod.GET)
	public String adminCategorie(Locale locale, Model model) {
		model.addAttribute("categories", metierCategorie.listerCategories());
		return "admin/categorieAdmin";
	}

	@RequestMapping(value = "/admin/nouvelleCategorie", method = RequestMethod.GET)
	public String nouvelleCategorie(Locale locale, Model model) {
		model.addAttribute("typeChamps", TypeChamp.values());
		model.addAttribute("champs", metierChamp.listerChamps());
		return "admin/nouvelleCategorie";
	}

	@RequestMapping(value = "/admin/creationCategorie", method = RequestMethod.GET)
	public String creationCategorie(Locale locale, Model model,
			@RequestParam("nomCategorie") String nomCategorie,@RequestParam("nomCategorie2") String nomCategorie2, String[] champs) {
		List<Champ> lesChamps = new ArrayList<Champ>();
		if (nomCategorie.equals("") || nomCategorie2.equals("")) {
			model.addAttribute("typeChamps", TypeChamp.values());
			model.addAttribute("champs", metierChamp.listerChamps());
			model.addAttribute("erreur", "erreur");
			return "admin/nouvelleCategorie";
		}
		if (champs != null) {
			for (String libelle : champs) {
				lesChamps.add(metierChamp.getChamp(libelle));
			}
		}
		metierCategorie.creerCategorie(nomCategorie+"-"+nomCategorie2, lesChamps);
		model.addAttribute("categories", metierCategorie.listerCategories());
		return "/admin/categorieAdmin";
	}
}
