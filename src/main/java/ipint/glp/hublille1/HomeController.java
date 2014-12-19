package ipint.glp.hublille1;

import ipint.glp.donnees.Annonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.donnees.Utilisateur;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.fabriques.FabUtilisateur;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;
import ipint.glp.metiers.MetierChamp;
import ipint.glp.metiers.MetierUtilisateur;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	private MetierCategorie metierCategorie = new MetierCategorie();

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	private void Init() {
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
		hm2.put("Description",
				"Bonjour je vends un livre très très interessant avec plein d'images !");
		hm2.put("Prix", "5");

		// Catégorie
		new MetierCategorie().creerCategorie("Objet", l);
		new MetierCategorie().creerCategorie("Covoiturage", l);

		// Utilisateur
		Utilisateur u = new MetierUtilisateur().creerUtilisateur("Smith",
				"John", "John.smith@gmail.com", Droit.DEFAUT);

		// Annonces
		new MetierAnnonce().creerAnnonce(
				new MetierCategorie().getCategorie("Objet"), u,
				TypeAnnonce.offre, hm);
		new MetierAnnonce().creerAnnonce(
				new MetierCategorie().getCategorie("Objet"), u,
				TypeAnnonce.offre, hm2);
		new MetierAnnonce().creerAnnonce(
				new MetierCategorie().getCategorie("Objet"), u,
				TypeAnnonce.offre, hm);
		new MetierAnnonce().creerAnnonce(
				new MetierCategorie().getCategorie("Objet"), u,
				TypeAnnonce.offre, hm2);
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

	@RequestMapping("/vueCreerAnnonce")
	public String vueCreerAnnonce(@RequestParam("categorieChoisie")String categorieChoisie, Model model) {
		System.err.println(categorieChoisie);
		model.addAttribute("lesChamps", metierCategorie.getCategorie(categorieChoisie).getChamps());
		return "creerAnnonce";
	}

	@RequestMapping(value = "/voirLesAnnoncesParCategorie", method = RequestMethod.GET)
	public ModelAndView voirLesAnnoncesParCategorie(String categorie) {

		return new ModelAndView("voirLesAnnoncesParCategorie", "lesAnnonces",
				metierAnnonce.listerAnnoncesParCategorie(categorie));
	}

//	@RequestMapping(value = "/init", method = RequestMethod.GET)
//	public String init(Locale locale, Model model) {
//		Init();
//		return "index";
//	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/annonces", method = RequestMethod.GET)
	public String annonces(Locale locale, Model model) {
		model.addAttribute("annonces", metierAnnonce.listerAnnonces());
		return "annonces";
	}
	
	@RequestMapping(value = "/bar", method = RequestMethod.GET)
	public String bar(Locale locale, Model model) {
		return "bar";
	}
	
	@RequestMapping(value = "/creationAnnonce", method = RequestMethod.GET)
	public String creerAnnonce(@RequestParam Map parameters) {
		HashMap<String,String> lesChamps = new HashMap<String, String>(parameters);
		for(Entry<String, String> entry : lesChamps.entrySet()) {
			System.err.println(entry.getKey());
			System.err.println(entry.getValue());
		}
		
		metierAnnonce.creerAnnonce(metierCategorie.getCategorie("biens"), new Utilisateur(), TypeAnnonce.offre, lesChamps);
		return "home";
	}
	
	@RequestMapping(value = "/choixCat", method = RequestMethod.GET)
	public String choixCategorie(Model model) {
		model.addAttribute("categories", metierCategorie.listerCategories());
		return "choixCategorie";
	}
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Model model) {
		Champ c1 = FabChamp.getInstance().creerChamp("titre", 60,
				TypeChamp.TEXTE);
		Champ c2 = FabChamp.getInstance()
				.creerChamp("description", 500, TypeChamp.TEXTE);
		Champ c3 = FabChamp.getInstance()
				.creerChamp("depart", 10, TypeChamp.TEXTE);
		Champ c4 = FabChamp.getInstance()
				.creerChamp("prix", 10, TypeChamp.TEXTE);
		List<Champ> champs1 = new ArrayList<Champ>();
		champs1.add(c1);
		champs1.add(c2);
		champs1.add(c3);
		champs1.add(c4);
		List<Champ> champs2 = new ArrayList<Champ>();
		champs2.add(c1);
		champs2.add(c2);
		champs2.add(c4);
		Categorie categorie = FabCategorie.getInstance().creerCategorie(
				"covoiturage", champs1);
		Categorie categorie2 = FabCategorie.getInstance().creerCategorie(
				"biens", champs2);
		Utilisateur utilisateur = FabUtilisateur.getInstance()
				.creerUtilisateur("toto", "titi", "toto.titi@gmail.com",
						Droit.ADMIN);
		return "home";
	}
}
