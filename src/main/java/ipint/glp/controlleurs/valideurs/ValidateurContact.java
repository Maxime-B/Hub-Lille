package ipint.glp.controlleurs.valideurs;


import ipint.glp.controlleurs.forms.FormAnnonce;
import ipint.glp.controlleurs.forms.FormContact;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidateurContact implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return FormContact.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object arg0, Errors e) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "emeteur",
				"annonce.contacter.erreur.emetteur.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "objet",
				"annonce.contacter.erreur.objet.requis");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "message",
				"annonce.contacter.erreur.message.requis");
	}


}