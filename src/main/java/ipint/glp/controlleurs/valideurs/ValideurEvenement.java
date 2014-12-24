package ipint.glp.controlleurs.valideurs;

import ipint.glp.donnees.Evenement;

import java.util.Date;

import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValideurEvenement implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Evenement.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "titre",
				"erreur.titre.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dateDebut",
				"erreur.dateDebut.requis");
		Evenement evenement = (Evenement) obj;
		if (evenement.getDateDebut() != null && evenement.getDateDebut().before(new Date())) {
			e.rejectValue("dateDebut", "erreur.dateDebut.posterieur");
		}
	}

}
