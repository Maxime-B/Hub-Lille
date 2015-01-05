package ipint.glp.controlleurs.valideurs;

import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.compositeKey.IdEvenement;
import ipint.glp.metiers.MetierEvenement;

import java.util.Date;

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
		if (evenement.getDateDebut() != null && evenement.getTitre() != null) {
			IdEvenement idEvenement = new IdEvenement(evenement.getTitre(), evenement.getDateDebut());
			if (MetierEvenement.getInstance().obtenir(idEvenement) != null) {
					e.rejectValue("id", "evenement.erreur.unique");
			}
		}
		if (evenement.getDateDebut() != null && evenement.getDateDebut().before(new Date())) {
			e.rejectValue("dateDebut", "evenement.erreur.dateDebut.posterieur");
		}
		if (evenement.getDescription() != null && evenement.getDescription().length() > 400) {
			e.rejectValue("description", "evenement.erreur.description.maxlength");
		}
	}

}
