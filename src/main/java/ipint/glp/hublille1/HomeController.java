package ipint.glp.hublille1;

import ipint.glp.donnees.Annonce;
import ipint.glp.metiers.MetierAnnonce;

import java.text.DateFormat;
import java.util.Date;
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

/**
 * Handles requests for the application home page. Nouveau commit (gbergeus, Clément)
 * test soukaina
 * plop bergeus
 * hind
 * hind
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private MetierAnnonce metierAnnonce = new MetierAnnonce();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/creerAnnonce", method = RequestMethod.POST)
	public String creerAnnonce(@ModelAttribute("command") Annonce annonce,
			BindingResult result) {
		
		metierAnnonce.creerAnnonce(annonce.getCategorie(), annonce.getUtilisateur(), annonce.getType(), null);

		return "annonceCree";
	}
	
	@RequestMapping("/vueCreerAnnonce")
	public ModelAndView vueCreerAnnonce() {
		return new ModelAndView("vueCreerAnnonce", "command", new Annonce());
	}
	
	
	@RequestMapping(value = "/voirLesAnnoncesParCategorie", method = RequestMethod.GET)
	public ModelAndView voirLesAnnoncesParCategorie(String categorie) {
		
		return new ModelAndView("voirLesAnnoncesParCategorie", "lesAnnonces", metierAnnonce.listerAnnoncesParCategorie(categorie));
	}
	
	@RequestMapping(value = "/voirLesAnnonces", method = RequestMethod.GET)
	public ModelAndView voirLesAnnonces() {
		
		return new ModelAndView("voirLesAnnonces", "lesAnnonces", metierAnnonce.listerAnnonces());
	}
	

	
}
