package ipint.glp.controlleurs;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.fabriques.FabEvenement;
import ipint.glp.fabriques.FabJob;
import ipint.glp.metiers.MetierUtilisateur;
import ipint.glp.metiers.exceptions.AuMoinsUnAdminException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import connexion.Connexion;

@Controller
public class ControlleurAccueil {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurAccueil.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("annonces", FabAnnonce.getInstance()
				.listerAnnonces());
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("annonces", FabAnnonce.getInstance()
				.listerAnnonces());
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		model.addAttribute("annonces", FabAnnonce.getInstance()
				.listerAnnonces());
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "test";
	}
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Model model) {
		/*
		 * Champ c1 = FabChamp.getInstance().creerChamp("titre", 60,
		 * TypeChamp.TEXTE,true); Champ c2 = FabChamp.getInstance()
		 * .creerChamp("description", 500, TypeChamp.TEXTEAREA,true);
		 */
		Champ c3 = FabChamp.getInstance().creerChamp("depart",
				TypeChamp.TEXTE, true);
		Champ c4 = FabChamp.getInstance().creerChamp("arrivee",
				TypeChamp.TEXTE, true);
		Champ c5 = FabChamp.getInstance().creerChamp("date",
				TypeChamp.DATE, true);
		Champ c6 = FabChamp.getInstance().creerChamp("prix",
				TypeChamp.NUMERIQUE, false);
		
		List<Champ> champs1 = new ArrayList<Champ>();
		// champs1.add(c1);
		// champs1.add(c2);
		champs1.add(c3);
		champs1.add(c4);
		champs1.add(c5);
		champs1.add(c6);
		

		List<Champ> champs2 = new ArrayList<Champ>();
		// champs2.add(c1);
		// champs2.add(c2);
		champs2.add(c6);
		
		Categorie categorie = FabCategorie.getInstance().creerCategorie(
				"Covoiturage", champs1);

		Categorie categorie2 = FabCategorie.getInstance().creerCategorie(
				"Biens", champs2);

		// utilisateurs avec roles par defaut
			MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
			Set<Droit> droits = new HashSet<Droit>();
			droits.add(Droit.SUPER_ADMIN);
			try {
				metierUtilisateur.modifierDroit("clement.duhaupas", droits);
				metierUtilisateur.modifierDroit("guillaume.bergeus", droits);
				metierUtilisateur.modifierDroit("hind.bahaoui", droits);
				metierUtilisateur.modifierDroit("latifou.sano", droits);
				metierUtilisateur.modifierDroit("maxime.briche", droits);
				metierUtilisateur.modifierDroit("soukaina.bekkai", droits);
			} catch (AuMoinsUnAdminException e) {
				e.printStackTrace();
			}
		// fin utilisateurs avec roles par defaut
		return "index";
	}
	
	@RequestMapping(value = "/close", method = RequestMethod.GET)
	public String close(Model model) {
		Connexion.getConnexion().fermerConnexion();
		return "index";
	}
	
	/*
	 * private void init2() { // champs (Objet) ArrayList<Champ> l = new
	 * ArrayList<Champ>(); l.add(new MetierChamp().creerChamp("Titre", 50,
	 * TypeChamp.TEXTE,true)); l.add(new MetierChamp().creerChamp("Description",
	 * 500, TypeChamp.TEXTEAREA,true)); l.add(new
	 * MetierChamp().creerChamp("Prix", 10, TypeChamp.NUMERIQUE,true));
	 * 
	 * HashMap<String, String> hm = new HashMap<String, String>();
	 * hm.put("Titre", "Vend une chaise"); hm.put("Description",
	 * "Bonjour je vends une chaise toute belle !"); hm.put("Prix", "15");
	 * 
	 * HashMap<String, String> hm2 = new HashMap<String, String>();
	 * hm2.put("Titre", "Vend un livre"); hm2.put("Description",
	 * "Bonjour je vends un livre très très interessant avec plein d'images !");
	 * hm2.put("Prix", "5");
	 * 
	 * // Catègorie new MetierCategorie().creerCategorie("Objet", l); new
	 * MetierCategorie().creerCategorie("Covoiturage", l);
	 */

	// // Utilisateur
	// Utilisateur u = new MetierUtilisateur().creerUtilisateur("Smith",
	// "John", "John.smith@gmail.com", Droit.DEFAUT);
	//
	// // Annonces
	// new MetierAnnonce().creerAnnonce(
	// new MetierCategorie().getCategorie("Objet"), u,
	// TypeAnnonce.offre, hm);
	// new MetierAnnonce().creerAnnonce(
	// new MetierCategorie().getCategorie("Objet"), u,
	// TypeAnnonce.offre, hm2);
	// new MetierAnnonce().creerAnnonce(
	// new MetierCategorie().getCategorie("Objet"), u,
	// TypeAnnonce.offre, hm);
	// new MetierAnnonce().creerAnnonce(
	// new MetierCategorie().getCategorie("Objet"), u,
	// TypeAnnonce.offre, hm2);

}
