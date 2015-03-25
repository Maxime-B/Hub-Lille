package ipint.glp.controlleurs.valideurs;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.donnees.Categorie;
import ipint.glp.donnees.Champ;
import ipint.glp.metiers.MetierAnnonce;
import ipint.glp.metiers.MetierCategorie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValideurAnnonce implements Validator {
	private static final Logger logger = LoggerFactory
			.getLogger(ValideurAnnonce.class);

	private MetierCategorie metierCategorie = new MetierCategorie();
	
	@Override
	public boolean supports(Class<?> arg0) {
		return FormAnnonce.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors e) {
		logger.debug("Une annonce est en validation", arg0);
		FormAnnonce formAnnonce = (FormAnnonce) arg0;

		// categorie
		Categorie categorie = metierCategorie.getCategorie(formAnnonce.getCategorie());
		if (categorie == null) {
			return;
		}
		 

		// titre et description
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "titre",
				"annonce.erreur.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"annonce.erreur.requis");

		int limiteTitre = 50;
		int limiteDescription = 200;
		if (formAnnonce.getTitre().length() > limiteTitre) {
			e.rejectValue("titre", "annonce.erreur.maxlength",
					new Object[] { limiteTitre }, null);
		}
		if (formAnnonce.getDescription().length() > limiteDescription) {
			e.rejectValue("descritpion", "annonce.erreur.maxlength",
					new Object[] { limiteDescription }, null);
		}

		// le reste des champs
		for (Champ champ : categorie.getChamps()) {
			String libelle = champ.getLibelle();
			String path = champ.getTypeChamp().name().toLowerCase() + "['" + libelle + "']";
			String value = formAnnonce.getLesChamps().get(libelle);

			if (champ.isObligatoire()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(e, path,
						"annonce.erreur.requis");
			}

		}
	}
}