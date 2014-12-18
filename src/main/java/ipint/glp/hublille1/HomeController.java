package ipint.glp.hublille1;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;
import ipint.glp.metiers.MetierChamp;
import ipint.glp.metiers.MetierUtilisateur;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	private void Init(){
		// champs (Objet)
		ArrayList<Champ> l = new ArrayList<Champ>();
		l.add(new MetierChamp().creerChamp("Titre", 50, TypeChamp.TEXTE));
		l.add(new MetierChamp().creerChamp("Description", 500, TypeChamp.TEXTE));
		l.add(new MetierChamp().creerChamp("Prix", 10, TypeChamp.NUMERIQUE));
		
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Titre", "Vend une chaise");
		hm.put("Description", "Bonjour je vends une chaise toute belle !");
		hm.put("Prix", "15");
		
		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("Titre", "Vend un livre");
		hm2.put("Description", "Bonjour je vends un livre très très interessant avec plein d'images !");
		hm2.put("Prix", "5");
		
		// Catégorie 
		new MetierCategorie().creerCategorie("Objet", l);
		new MetierCategorie().creerCategorie("Covoiturage", l);
		
		// Utilisateur
		Utilisateur u = new MetierUtilisateur().creerUtilisateur("Smith", "John", "John.smith@gmail.com", Droit.DEFAUT);
				
		// Annonces
		new MetierAnnonce().creerAnnonce(new MetierCategorie().getCategorie("Objet"), u , TypeAnnonce.offre, hm);
		new MetierAnnonce().creerAnnonce(new MetierCategorie().getCategorie("Objet"), u , TypeAnnonce.offre, hm2);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/creerAnnonce", method = RequestMethod.POST)
	public String creerAnnonce(@ModelAttribute("command") Annonce annonce,
			BindingResult result) {

		metierAnnonce.creerAnnonce(annonce.getCategorie(),
				annonce.getUtilisateur(), annonce.getType(), null);

		return "annonceCree";
	}

	@RequestMapping("/vueCreerAnnonce")
	public ModelAndView vueCreerAnnonce() {
		return new ModelAndView("vueCreerAnnonce", "command", new Annonce());
	}

	@RequestMapping(value = "/voirLesAnnoncesParCategorie", method = RequestMethod.GET)
	public ModelAndView voirLesAnnoncesParCategorie(String categorie) {

		return new ModelAndView("voirLesAnnoncesParCategorie", "lesAnnonces",
				metierAnnonce.listerAnnoncesParCategorie(categorie));
	}

	@RequestMapping(value = "/voirLesAnnonces", method = RequestMethod.GET)
	public ModelAndView voirLesAnnonces() {

		return new ModelAndView("voirLesAnnonces", "lesAnnonces",
				metierAnnonce.listerAnnonces());
	}

}
