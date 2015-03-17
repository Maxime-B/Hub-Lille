package ipint.glp.controlleurs;

import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Droit;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.TypeAnnonce;
import ipint.glp.donnees.TypeChamp;
import ipint.glp.fabriques.FabAnnonce;
import ipint.glp.fabriques.FabCategorie;
import ipint.glp.fabriques.FabChamp;
import ipint.glp.fabriques.FabEvenement;
import ipint.glp.fabriques.FabJob;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierEvenement;
import ipint.glp.metiers.MetierJob;
import ipint.glp.metiers.MetierUtilisateur;
import ipint.glp.metiers.exceptions.AuMoinsUnAdminException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
				.listerAnnonces(TypeAnnonce.offre));
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("annonces", FabAnnonce.getInstance()
				.listerAnnonces(TypeAnnonce.offre));
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "index";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		model.addAttribute("annonces", FabAnnonce.getInstance()
				.listerAnnonces(TypeAnnonce.offre));
		model.addAttribute("evenements", FabEvenement.getInstance().lister());
		model.addAttribute("jobs", FabJob.getInstance().listerJob());
		return "test";
	}
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Model model) throws ParseException {

		//creation des champs
		Champ c3 = FabChamp.getInstance().creerChamp("Départ-Departure",
				TypeChamp.TEXTE, true);
		Champ c4 = FabChamp.getInstance().creerChamp("Arrivée-Arrival",
				TypeChamp.TEXTE, true);
		Champ c5 = FabChamp.getInstance().creerChamp("Date-Date",
				TypeChamp.DATE, true);
		Champ c6 = FabChamp.getInstance().creerChamp("Prix-Price",
				TypeChamp.NUMERIQUE, false);

		
		//creation des categories
		List<Champ> champs1 = new ArrayList<Champ>();
		champs1.add(c3);
		champs1.add(c4);
		champs1.add(c5);
		champs1.add(c6);
		List<Champ> champs2 = new ArrayList<Champ>();
		champs2.add(c6);
		Categorie categorie = FabCategorie.getInstance().creerCategorie(
				"Covoiturage-Carpooling", champs1);
		Categorie categorie2 = FabCategorie.getInstance().creerCategorie(
				"Biens-Goods", champs2);

		// creation des utilisateurs
		MetierUtilisateur metierUtilisateur = new MetierUtilisateur();
			try {
				
				Set<Droit> droits = new HashSet<Droit>();
				droits.add(Droit.SUPER_ADMIN);
				Set<Droit> droits2 = new HashSet<Droit>();
				droits2.add(Droit.SUPER_ADMIN);
				Set<Droit> droits3 = new HashSet<Droit>();
				droits3.add(Droit.MODERATEUR);
				Set<Droit> droits4 = new HashSet<Droit>();
				droits4.add(Droit.VIE_ETUDIANTE);
				Set<Droit> droits5 = new HashSet<Droit>();
				droits5.add(Droit.ASSOCIATION);
				metierUtilisateur.modifierRole("maxime.briche", droits);
				metierUtilisateur.modifierRole("clement.duhaupas", droits2);
				metierUtilisateur.modifierRole("guillaume.bergeus", droits3);
				metierUtilisateur.modifierRole("hind.bahaoui", droits4);
				metierUtilisateur.modifierRole("latifou.sano", droits5);
				
			} catch (AuMoinsUnAdminException e) {
				e.printStackTrace();
			}
			
			//creation d'annonce
			MetierAnnonce metierAnnonce = new MetierAnnonce();
			HashMap<String, String> lesChamps = new HashMap<String,String>();
			lesChamps.put("Départ-Departure", "Paris");
			lesChamps.put("Arrivée-Arrival", "Marseille");
			lesChamps.put("Date-Date", "2015-05-24");
			lesChamps.put("Prix-Price", "30");
			metierAnnonce.creerAnnonce(categorie, "Paris Marseille", "Trajet de 8 heures", metierUtilisateur.getUtilisateur("maxime.briche"), TypeAnnonce.offre, lesChamps);
			lesChamps = new HashMap<String,String>();
			lesChamps.put("Prix-Price", "30");
			metierAnnonce.creerAnnonce(categorie2, "Megane 3", "50 000km ABS vitre electrique...", metierUtilisateur.getUtilisateur("guillaume.bergeus"), TypeAnnonce.offre, lesChamps);
			
			//creation d'evenement
			Evenement eve = new Evenement();
			eve.setTitre("Concert MDE");
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.add(Calendar.DAY_OF_YEAR, 1);
			eve.setDateDebut(gregorianCalendar.getTime());
			eve.setDescription("Concert de l'association des étudiants. Boissons à volonté !");
			eve.setLieu("MDE");
			eve.setUtilisateur(metierUtilisateur.getUtilisateur("latifou.sano"));
			MetierEvenement.getInstance().creer(eve);
			
			eve = new Evenement();
			eve.setTitre("Remise des diplômes MIAGE");
			gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.add(Calendar.DAY_OF_YEAR, 1);
			eve.setDateDebut(gregorianCalendar.getTime());
			eve.setDescription("Remise des diplômes aux anciens de la promo. Buffet gratuit et champagne à gogooooo !");
			eve.setLieu("M5");
			eve.setUtilisateur(metierUtilisateur.getUtilisateur("latifou.sano"));
			MetierEvenement.getInstance().creer(eve);
			
			//creation job
			MetierJob metierJob = new MetierJob();
			metierJob.creerJob("Serveur au RU", "500 euros par mois", "Besoin d'un serveur du lundi au vendredi pendant 6 mois", "Etre sociable et dynamique", metierUtilisateur.getUtilisateur("hind.bahaoui"));
			metierJob.creerJob("Traduction de texte", "800 euros par mois", "Nous cherchons quelqu'un capable de traduire des textes d'anglais en français", "Etre sociable et dynamique. Bilingue", metierUtilisateur.getUtilisateur("hind.bahaoui"));
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
