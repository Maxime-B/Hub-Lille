package ipint.glp.controlleurs.valideurs;

import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.donnees.Champ;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValideurAnnonce implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return FormAnnonce.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors e) {
		FormAnnonce formAnnonce = (FormAnnonce) arg0;

		ValidationUtils.rejectIfEmptyOrWhitespace(e, "categorie",
				"annonce.erreur.requis");
		if (e.hasErrors()) {
			return;
		}
		if (formAnnonce == null || formAnnonce.getCategorieObject() == null || formAnnonce.getCategorieObject().getChamps() == null) {
			e.rejectValue("categorie", "annonce.erreur.categorie.invalide");
			return;
		}
		
		for (Champ champ : formAnnonce.getCategorieObject().getChamps()) {
			String libelle = champ.getLibelle();
			String path = "lesChamps['" +libelle + "']";
			String value = formAnnonce.getLesChamps().get(libelle);
			
			if (champ.isObligatoire()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(e, path,
						"annonce.erreur.requis");
			}
			
			if (value != null && champ.getLimite() != null && value.length() > champ.getLimite()) {
				e.rejectValue(path, "annonce.erreur.maxlength",
						new Object[] {champ.getLimite() }, null);
			}
		}
	}
}