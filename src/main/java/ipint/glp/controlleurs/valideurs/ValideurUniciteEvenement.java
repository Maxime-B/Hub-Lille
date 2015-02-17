package ipint.glp.controlleurs.valideurs;

import ipint.glp.donnees.Evenement;
import ipint.glp.metiers.MetierEvenement;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValideurUniciteEvenement implements Validator {

	@Override
	public boolean supports(Class<?> c) {
		return Evenement.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object obj, Errors e) {
		Evenement evenement = (Evenement) obj;
		if (evenement.getDateDebut() != null && evenement.getTitre() != null) {
			if (MetierEvenement.getInstance().obtenir(evenement.getTitre(), evenement.getDateDebut()) != null) {
					e.rejectValue("id", "evenement.erreur.unique");
			}
		}
	}

}
