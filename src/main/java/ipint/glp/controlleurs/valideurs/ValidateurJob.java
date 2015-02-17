package ipint.glp.controlleurs.valideurs;

import java.util.Date;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.donnees.Champ;
import ipint.glp.donnees.Evenement;
import ipint.glp.donnees.Job;
//import ipint.glp.donnees.compositeKey.IdEvenement;
import ipint.glp.metiers.MetierEvenement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidateurJob implements Validator {
	private static final Logger logger = LoggerFactory
			.getLogger(ValideurAnnonce.class);

	

	@Override
	public void validate(Object obj, Errors e) {

		// titre et description
		System.out.println("test validate");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "titre",
				"job.erreur.titre.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"job.erreur.description.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "modalite",
				"job.erreur.modalite.requis");
		Job job = (Job) obj;
		if (job.getDescription() != null && job.getDescription().length() > 400) {
			e.rejectValue("description", "evenement.erreur.description.maxlength");
		}

		// le reste des champs
		/*for (Champ champ : formAnnonce.getCategorieObject().getChamps()) {
			String libelle = champ.getLibelle();
			String path = champ.getTypeChamp().name().toLowerCase() + "['" + libelle + "']";
			String value = formAnnonce.getLesChamps().get(libelle);

			if (champ.isObligatoire()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(e, path,
						"annonce.erreur.requis");
			}

			if (value != null && champ.getLimite() != null
					&& value.length() > champ.getLimite()) {
				e.rejectValue(path, "annonce.erreur.maxlength",
						new Object[] { champ.getLimite() }, null);
			}
		}*/
	}



	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}