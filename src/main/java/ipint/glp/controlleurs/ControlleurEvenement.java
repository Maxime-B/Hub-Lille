package ipint.glp.controlleurs;

import ipint.glp.controlleurs.valideurs.ValideurEvenement;
import ipint.glp.donnees.Evenement;
import ipint.glp.metiers.MetierEvenement;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControlleurEvenement {
	private static final Logger logger = LoggerFactory
			.getLogger(ControlleurEvenement.class);
	private MetierEvenement metierEvenement = MetierEvenement.getInstance();
	
	/**
	* Register a validator that will be lookup when a parameter is binded to a handler
	* argument (with @ModelAttribute() for example).
	* @param binder
	*/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	// register the ContactValidator used to validate objects of type Contact.
	binder.setValidator(new ValideurEvenement() );
	}

	
	@RequestMapping(value = "/evenement/creer", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model,
			@Valid @ModelAttribute("evenement") Evenement evenement,
			BindingResult bindingResultOfEvenement) {
		if (bindingResultOfEvenement.hasErrors()) {
			return new ModelAndView("evenement/creer", "evenement", evenement);
		}
		metierEvenement.creer(evenement);
		model.addAttribute("evenement", evenement);
		return new ModelAndView("evenement/creer", "evenement", evenement);
	}
}
