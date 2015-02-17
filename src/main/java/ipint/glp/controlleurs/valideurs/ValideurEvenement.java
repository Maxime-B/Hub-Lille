package ipint.glp.controlleurs.valideurs;

import ipint.glp.donnees.Evenement;

import java.util.Date;
import java.util.GregorianCalendar;

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
				"evenement.erreur.titre.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "dateDebut",
				"evenement.erreur.dateDebut.requis");
		Evenement evenement = (Evenement) obj;

		if (evenement.getDateDebut() != null) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(evenement.getDateDebut());
			GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
			if (gregorianCalendar.get(GregorianCalendar.YEAR) < gregorianCalendar2
					.get(GregorianCalendar.YEAR)
					|| (gregorianCalendar.get(GregorianCalendar.YEAR) == gregorianCalendar2
							.get(GregorianCalendar.YEAR) && gregorianCalendar
							.get(GregorianCalendar.DAY_OF_YEAR) < gregorianCalendar2
							.get(GregorianCalendar.DAY_OF_YEAR))) {
				e.rejectValue("dateDebut",
						"evenement.erreur.dateDebut.posterieur");
			}
		}
		if (evenement.getDescription() != null
				&& evenement.getDescription().length() > 400) {
			e.rejectValue("description",
					"evenement.erreur.description.maxlength");
		}
	}

}
