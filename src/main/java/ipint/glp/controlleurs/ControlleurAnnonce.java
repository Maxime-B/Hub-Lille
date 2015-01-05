package ipint.glp.controlleurs;

import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurAnnonce {

	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAnnonce.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierCategorie metierCategorie = new MetierCategorie();

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping("/annonce/creer")
	public String creerAnnonce(@RequestParam("categorieChoisie")String categorieChoisie, Model model) {
		System.err.println(categorieChoisie);
		model.addAttribute("lesChamps", metierCategorie.getCategorie(categorieChoisie).getChamps());
		return "annonce/creer";
	}
	
	@RequestMapping(value = "/annonce/creer", method = RequestMethod.POST)
	public String creerAnnonce(@RequestParam Map parameters) {
		HashMap<String,String> lesChamps = new HashMap<String, String>(parameters);
		for(Entry<String, String> entry : lesChamps.entrySet()) {
			System.err.println(entry.getKey());
			System.err.println(entry.getValue());
		}
		metierAnnonce.creerAnnonce(metierCategorie.getCategorie("biens"), new Utilisateur(), TypeAnnonce.offre, lesChamps);
		return "redirect:/annonce";
	}
	
	@RequestMapping(value = "/voirLesAnnoncesParCategorie", method = RequestMethod.GET)
	public ModelAndView voirLesAnnoncesParCategorie(String categorie) {
		return new ModelAndView("voirLesAnnoncesParCategorie", "lesAnnonces",
				metierAnnonce.listerAnnoncesParCategorie(categorie));
	}
	
	@RequestMapping(value = "/annonce", method = RequestMethod.GET)
	public String listerAnnonces(Locale locale, Model model) {
		model.addAttribute("annonces", metierAnnonce.listerAnnonces());
		return "annonce/lister";
	}
	
	@RequestMapping(value = "/annonce/categorie/choisir", method = RequestMethod.GET)
	public String choixCategorie(Model model) {
		model.addAttribute("categories", metierCategorie.listerCategories());
		return "annonce/categorie/choisir";
	}
}
